/*
 * Copyright 2022 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.amplify.amplify_datastore.types.model

import com.amazonaws.amplify.amplify_core.cast
import com.amplifyframework.core.model.ModelIndex

data class FlutterModelIndex(val map: Map<String, Any>) {
    val name: String = map["name"] as String? ?: unnamedIndexKey
    val fields: List<String> = when (val fields = map["fields"]) {
        is List<*> -> fields.cast()
        else -> emptyList()
    }

    fun convertToNativeModelIndex(): ModelIndex {
        return ModelIndex.builder()
            .indexName(name)
            .indexFieldNames(fields)
            .build()
    }

    companion object {
        const val unnamedIndexKey = "undefined"
    }
}
