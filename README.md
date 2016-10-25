### Serializer

Serializer is a tiny utility that allows you to de/serialize object from/to JSON blob. This in a way allows 
you to save the JSON blob as a string in a `SharedPreference` or in a database. 

By default, `Serializer` comes with `Gson` and `Moshi` as the underlining serialization engines. Gson is used 
by default with Serializer but can easily be overwritten to make use of either `Moshi` or any other 
serialization engine. Eg. `Jackson` or custom made one.

You can use it with tools like [Tray](https://github.com/grandcentrix/tray/), which is a `SharedPreferences` with 
multi-process support. This [issue](https://github.com/grandcentrix/tray/issues/7) inspired this library.

## How to integrate into your app?

To integrate the library into your app, you need to make a few changes in the `build.gradle` file 
as follows:

**Step 1.** 
Add the JitPack repository to your build file. Add it in your root `build.gradle` in the `repositories` block:

```java
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
**Step 2.** 
Add the dependency
```java
dependencies {
    compile 'com.github.eyedol:serializer:1.0.0'
}
```

## How to use the library
Let's say you want to use the default `Gson` to serialize a model named `Cinema` then save it into a `SharedPreference`.

```java
// Build default serializer object
Serializer mSerializer = new Serializer.Builder().build();
Cinema cinema = new Cinema("addhen cinemas", "accra");

// Serialize into a JSON string
String cinemaJson = mSerializer.strategy(Cinema.class).serialze(cinema);

// Get the shared preferences
final SharedPreferences sharedPreferences = context.getSharedPreferences("android-shared-prefs", MODE_PRIVATE);

// Save the object into it as a json string
sharedPreferences.edit().putString("cinema", cinemaJson).apply();

// Now retrieve it and deserialize it back into the object.
cinemaJson = sharedPreferences.getString("cinema", null);
cinema = mSerializer.strategy(Cinema.class).deserialize(cinemaJson);
```

Let's say you're familiar with `Moshi` and wants to use that instead.

```java
// Build serializer object with moshi
MoshiSerializationStrategyFactory factory = MoshiSerializationStrategyFactory.create();

Serializer mSerializer = new Serializer.Builder()
                .setSerializationStrategyFactory(factory)
                .build();

Cinema cinema = new Cinema("addhen cinemas", "accra");
List<Cinema> cinemas = new ArrayList<>();
cinemas.add(cinema);
        
// Serialize into a JSON string
String cinemasJson = mSerializer.strategy(List.class).serialize(cinemas);

// Get the shared preferences
final SharedPreferences sharedPreferences = context.getSharedPreferences("android-shared-prefs", MODE_PRIVATE);

// Save the list into it as a json string
sharedPreferences.edit().putString("cinemas", cinemasJson).apply();

// Now retrieve it and deserialize it back into a list object.
cinemasJson = sharedPreferences.getString("cinemas", null);
cinemas = Arrays.asList(mSerializer.strategy(Cinema[].class).deserialize(cinemasJson));
```

For more usage examples, look inside [test](https://github.com/addhen/serilaizer/blob/master/src/test/java/com/addhen/serializer/SerializerTest.java).