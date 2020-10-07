<h1 align="center">SimpleRecyclerView</h1></br>

<p align="center">
A SimpleRecyclerView with databinding for andorid list
</p>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</p>

### Dependency Gradle 
Add below codes to your **root** `build.gradle` file (not your module build.gradle file).
```
allprojects {
    repositories {
        jcenter()
    }
}
```

And add a dependency code to your **module**'s `build.gradle` file.
```gradle
buildFeatures {
  dataBinding true
}
```

```gradle
dependencies {
  implementation 'com.github.KennethSS:SimpleRecyclerView:x.y.z'
}
```


## Usage
### Basic Example

#### Prepare to making item
```xml
<layout>    
    <data>
        <!-- You should to declare for Item -->
        <variable
            name="item"
            type="com.solar.recyclerviewsample.Food" />

        <!-- If you wan't to use that you have not to declare -->
        <variable
            name="vm"
            type="com.solar.recyclerviewsample.FoodViewModel" />
    </data>
</layout>
```

```kotlin
data class Food (
    val title: String,
    val subtitle: String,
    val img: Int,
    override val layoutRes: Int = R.layout.item_food_menu
) : ItemType
```

```kotlin
main_basic_recycler_view.adapter = DataBindingAdapter<Food>(
            FoodViewModel()
       ).apply {
            submitList(foods)
       }
```
