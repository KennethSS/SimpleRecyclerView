package com.solar.recyclerviewsample.model.movie

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
object MovieFactory {
    fun getMovieList(): List<Movie> {
        return mutableListOf(
            Movie("https://image.tmdb.org/t/p/w500/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg", "The Croods: A New Age", "2020-11-25"),
            Movie("https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg", "Wonder Woman 1984", "2020-12-16"),
            Movie("https://image.tmdb.org/t/p/w500/zeD4PabP6099gpE0STWJrJrCBCs.jpg", "Honest Thief", "2020-09-03"),
            Movie("https://image.tmdb.org/t/p/w500/bNo2mcvSwIvnx8K6y1euAc1TLVq.jpg", "Greenland", "2020-07-29"),
            Movie("https://image.tmdb.org/t/p/w500/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg", "반도", "2020-07-15"),
            Movie("https://image.tmdb.org/t/p/w500/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg", "The Croods: A New Age", "2020-11-25"),
            Movie("https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg", "Wonder Woman 1984", "2020-12-16"),
            Movie("https://image.tmdb.org/t/p/w500/zeD4PabP6099gpE0STWJrJrCBCs.jpg", "Honest Thief", "2020-09-03"),
            Movie("https://image.tmdb.org/t/p/w500/bNo2mcvSwIvnx8K6y1euAc1TLVq.jpg", "Greenland", "2020-07-29"),
            Movie("https://image.tmdb.org/t/p/w500/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg", "반도", "2020-07-15"),
            Movie("https://image.tmdb.org/t/p/w500/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg", "The Croods: A New Age", "2020-11-25"),
            Movie("https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg", "Wonder Woman 1984", "2020-12-16"),
            Movie("https://image.tmdb.org/t/p/w500/zeD4PabP6099gpE0STWJrJrCBCs.jpg", "Honest Thief", "2020-09-03"),
            Movie("https://image.tmdb.org/t/p/w500/bNo2mcvSwIvnx8K6y1euAc1TLVq.jpg", "Greenland", "2020-07-29"),
            Movie("https://image.tmdb.org/t/p/w500/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg", "반도", "2020-07-15")
        )
    }
}