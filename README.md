# Robolectric ClassLoader Test
Loads classes differently depending on platform.

Take a look at this [test](app/src/test/java/com/motorro/classloadertest/ExampleUnitTest.kt)

[PhoneUtils](https://github.com/googlei18n/libphonenumber) load metadata using the following code:
```java
  static final MetadataLoader DEFAULT_METADATA_LOADER = new MetadataLoader() {
    @Override
    public InputStream loadMetadata(String metadataFileName) {
      return MetadataManager.class.getResourceAsStream(metadataFileName);
    }
  };
```

When configuring test with: 
```
    @Config(
            constants = BuildConfig::class,
            sdk = intArrayOf(Build.VERSION_CODES.M)
    )
```
`JarUrlConnection` is set to:
```
sun.net.www.protocol.jar.JarURLConnection:jar:file:/C:/Users/motorro/.m2/repository/org/robolectric/android-all/6.0.0_r1-robolectric-0/android-all-6.0.0_r1-robolectric-0.jar!/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto_US
```
(refers roboelectric platform mock) and fails to read a file

When configuring test with: 
```
    @Config(
            constants = BuildConfig::class,
            sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP_MR1)
    )
```
`JarUrlConnection` is set to:
```
sun.net.www.protocol.jar.JarURLConnection:jar:file:/C:/Users/motorro/.gradle/caches/modules-2/files-2.1/com.googlecode.libphonenumber/libphonenumber/8.0.0/ce021971974ee6a26572e43eaba7edf184c3c63d/libphonenumber-8.0.0.jar!/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto_US
```
which is correct library file.

Here is [StackOverflow question](http://stackoverflow.com/questions/41246534/class-getresourceasstream-in-robolectric-environment-fails-to-return-correct-str)
