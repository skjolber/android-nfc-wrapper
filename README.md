[![Build Status](https://travis-ci.org/skjolber/android-nfc-wrapper.svg)](https://travis-ci.org/skjolber/android-nfc-wrapper)
[![Coverage Status](https://coveralls.io/repos/github/skjolber/android-nfc-wrapper/badge.svg?branch=master)](https://coveralls.io/github/skjolber/android-nfc-wrapper?branch=master)

# android-nfc-wrapper
Project wrapping black- and greylisted Android NFC classes. 

Features:

 - Clones a lot of NFC-related interfaces (aidls) and classes, like `Ndef`, `Tag` and `IsoDep`
 - Adds wrappers so that the cloned source works in parallel with the native sources

Advantages:

 * external readers can use the same pattern as internal NFC (i.e. parcelable objects reconnecting to services on deserialization).
 * working with dual NFC stacks is simplified, even on the latest Android; independent of which classes will be grey- or blacklisted later.
 
## License
[Apache 2.0].

# History
 - 1.0.0: Initial version extracted from the [external-nfc-api](https://github.com/skjolber/external-nfc-api) project.

[Apache 2.0]: 			http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:		https://github.com/skjolber/android-nfc-wrapper/issues
[Maven]:			http://maven.apache.org/

