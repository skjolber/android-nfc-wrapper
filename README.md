[![Build Status](https://travis-ci.org/skjolber/android-nfc-wrapper.svg)](https://travis-ci.org/skjolber/android-nfc-wrapper)

# android-nfc-wrapper
Project cloning + wrapping black- and greylisted Android NFC (`android.nfc`) classes. 

Features:

 - Clones a lot of NFC-related interfaces (aidls) and classes, like `Ndef`, `Tag` and `IsoDep`
 - Adds wrappers so that the cloned source works in parallel with the native sources

Advantages:

 * external readers can use the same pattern as internal NFC (i.e. parcelable objects reconnecting to services on deserialization).
 * working with dual NFC stacks is simplified, even on the latest Android; independent of which classes will be grey- or blacklisted later.

Disadvantages:

 * increased footprint due to additonal (duplicated) classes
 * does not work out of the box with third party libraries - they must be modified / recompiled to work. This can be solved with a custom processor, but this is yet to be implemented.
  
## License
[Apache 2.0]

## Obtain
The project is built with [Gradle] and is available on the central Maven repository. For Gradle, configure the property

```groovy
ext {
  androidNfcWrapperVersion = '1.0.1'
}
```

and add the dependency

```groovy
api("com.github.skjolber.android.nfc:wrapper:${androidNfcWrapperVersion}@aar")
```

# History
 - 1.0.1: Return null when tag technology is not in tech list.
 - 1.0.0: Initial version extracted from the [external-nfc-api](https://github.com/skjolber/external-nfc-api) project.

[Apache 2.0]: 			http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:		https://github.com/skjolber/android-nfc-wrapper/issues
[Gradle]:			https://gradle.org/

