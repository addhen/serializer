### Serializer

Serializer is a tiny utility that allows you to de/serialize object from/to JSON blob. This in a way allows 
you to save the JSON blob as a string in a `SharedPreference` or in a database. 

By default, `Serializer` comes `Gson` and `Moshi` as the underlining serialization engines. Gson is used 
by default with Serializer but can easily be overwrote to make use of either `Moshi` or any other 
serialization engine. Eg. `Jackson` or custom made

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
