package com.example.myapplication.cache.mapper

import androidx.room.Entity

interface EntityMapper<CACHED, ENTITY> {
    fun mapFromCached(model: CACHED): ENTITY
    fun mapToCached(model: ENTITY): CACHED
}