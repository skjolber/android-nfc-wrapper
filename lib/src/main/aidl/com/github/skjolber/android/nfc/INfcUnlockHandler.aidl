package com.github.skjolber.android.nfc;

import com.github.skjolber.android.nfc.TagImpl;

interface INfcUnlockHandler {

    boolean onUnlockAttempted(in TagImpl tag);

}
