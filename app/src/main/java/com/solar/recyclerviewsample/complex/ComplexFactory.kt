package com.solar.recyclerviewsample.complex

import com.solar.recyclerviewsample.model.food.FoodFactory
import com.solar.recyclerviewsample.model.movie.MovieFactory
import com.solar.recyclerviewsample.model.movie.MovieList

/**
 * Copyright 2020 Kenneth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/
object ComplexFactory {
    fun getComplexList(): List<AbstractComplexModel> {
        return mutableListOf(
            MovieList("Popular 1", MovieFactory.getMovieList()),
            MovieList("Popular 2", MovieFactory.getMovieList()),
            MovieList("Popular 3", MovieFactory.getMovieList()),
            MovieList("Popular 4", MovieFactory.getMovieList()),
            MovieList("Popular 5", MovieFactory.getMovieList()),
            MovieList("Popular 6", MovieFactory.getMovieList()),
            MovieList("Popular 7", MovieFactory.getMovieList()),
            MovieList("Popular 8", MovieFactory.getMovieList()),
            MovieList("Popular 9", MovieFactory.getMovieList()),
            MovieList("Popular 10", MovieFactory.getMovieList()),
            MovieList("Popular 11", MovieFactory.getMovieList()),
            MovieList("Popular 12", MovieFactory.getMovieList()),
            MovieList("Popular 13", MovieFactory.getMovieList()),
            MovieList("Popular 14", MovieFactory.getMovieList()),
            MovieList("Popular 15", MovieFactory.getMovieList()),
            MovieList("Popular 16", MovieFactory.getMovieList()),
            MovieList("Popular 17", MovieFactory.getMovieList()),
            MovieList("Popular 18", MovieFactory.getMovieList()),
            MovieList("Popular 19", MovieFactory.getMovieList()),
            MovieList("Popular 20", MovieFactory.getMovieList()),
            MovieList("Popular 21", MovieFactory.getMovieList()),
            MovieList("Popular 22", MovieFactory.getMovieList()),
            MovieList("Popular 23", MovieFactory.getMovieList()),
            MovieList("Popular 24", MovieFactory.getMovieList()),
            MovieList("Popular 25", MovieFactory.getMovieList()),
            MovieList("Popular 26", MovieFactory.getMovieList())
        )
    }
}