package com.vladan.technical_task_android.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.vladan.technical_task_android.model.Error
import com.vladan.technical_task_android.model.ErrorField


class ErrorsMoshiAdapter : JsonAdapter<Error>() {

    @Synchronized
    override fun fromJson(reader: JsonReader): Error? {
        if (reader.peek() === JsonReader.Token.NULL) {
            return reader.nextNull()
        }
        if (reader.peek() == JsonReader.Token.BEGIN_ARRAY) {
            val list = mutableListOf<ErrorField>()

            reader.beginArray()
            while (reader.hasNext()) {
                var field = ""
                var message = ""
                reader.beginObject()
                while (reader.hasNext()) {
                    when (reader.nextName()) {
                        "field" -> field = reader.nextString()
                        "message" -> message = reader.nextString()
                        else -> reader.skipValue()
                    }
                }
                reader.endObject()
                if (field.isBlank() || message.isBlank()) {
                    throw JsonDataException("Missing required field")
                }
                list.add(ErrorField(field, message))
            }
            reader.endArray()
            return Error(list, null)
        }

        var message = ""
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "message" -> message = reader.nextString()
                else -> reader.skipValue()
            }
        }
        if (message.isBlank()) {
            throw JsonDataException("Missing required field")
        }
        reader.endObject()

        return Error(emptyList(), message)
    }

    @Synchronized
    override fun toJson(writer: JsonWriter, value: Error?) {
        writer.nullValue()
    }

}