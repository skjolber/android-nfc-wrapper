/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.skjolber.android.nfc.tech;

import com.github.skjolber.android.nfc.Tag;
import com.github.skjolber.android.nfc.TagImpl;
import com.github.skjolber.android.nfc.TagWrapper;

import android.os.Bundle;
import android.os.RemoteException;

import java.io.IOException;

/**
 * Provides access to NFC-B (ISO 14443-3B) properties and I/O operations on a {@link TagImpl}.
 *
 * <p>Acquire a {@link NfcB} object using {@link #get}.
 * <p>The primary NFC-B I/O operation is {@link #transceive}. Applications must
 * implement their own protocol stack on top of {@link #transceive}.
 *
 * <p class="note"><strong>Note:</strong> Methods that perform I/O operations
 * require the {@link android.Manifest.permission#NFC} permission.
 */
public abstract class NfcB implements BasicTagTechnology {

    public static final String EXTRA_APPDATA = "appdata";

    public static final String EXTRA_PROTINFO = "protinfo";


    /**
     * Get an instance of {@link NfcBImpl} for the given tag.
     * <p>Returns null if {@link NfcBImpl} was not enumerated in {@link TagImpl#getTechList}.
     * This indicates the tag does not support NFC-B.
     * <p>Does not cause any RF activity and does not block.
     *
     * @param tag an NFC-B compatible tag
     * @return NFC-B object
     */

    public static NfcB get(Tag tag) {
        if(tag instanceof TagImpl) {
            TagImpl tagImpl = (TagImpl)tag;
            if (!tagImpl.hasTech(TagTechnology.NFC_B)) return null;
            try {
                return new NfcBImpl(tagImpl);
            } catch (RemoteException e) {
                return null;
            }
        } else if(tag instanceof TagWrapper) {
            TagWrapper delegate = (TagWrapper)tag;
            android.nfc.tech.NfcB nfcB = android.nfc.tech.NfcB.get(delegate.getDelegate());
            if(nfcB == null) {
                return null;
            }
            return new NfcBWrapper(nfcB);
        } else {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Return the Application Data bytes from ATQB/SENSB_RES at tag discovery.
     *
     * <p>Does not cause any RF activity and does not block.
     *
     * @return Application Data bytes from ATQB/SENSB_RES bytes
     */
    public abstract byte[] getApplicationData();

    /**
     * Return the Protocol Info bytes from ATQB/SENSB_RES at tag discovery.
     *
     * <p>Does not cause any RF activity and does not block.
     *
     * @return Protocol Info bytes from ATQB/SENSB_RES bytes
     */
    public abstract byte[] getProtocolInfo();

    /**
     * Send raw NFC-B commands to the tag and receive the response.
     *
     * <p>Applications must not append the EoD (CRC) to the payload,
     * it will be automatically calculated.
     * <p>Applications must not send commands that manage the polling
     * loop and initialization (SENSB_REQ, SLOT_MARKER etc).
     *
     * <p>Use {@link #getMaxTransceiveLength} to retrieve the maximum number of bytes
     * that can be sent with {@link #transceive}.
     *
     * <p>This is an I/O operation and will block until complete. It must
     * not be called from the main application thread. A blocked call will be canceled with
     * {@link IOException} if {@link #close} is called from another thread.
     *
     * <p class="note">Requires the {@link android.Manifest.permission#NFC} permission.
     *
     * @param data bytes to send
     * @return bytes received in response
     * @throws android.nfc.TagLostException if the tag leaves the field
     * @throws IOException if there is an I/O failure, or this operation is canceled
     */
    public abstract byte[] transceive(byte[] data) throws IOException;

    /**
     * Return the maximum number of bytes that can be sent with {@link #transceive}.
     * @return the maximum number of bytes that can be sent with {@link #transceive}.
     */
    public abstract int getMaxTransceiveLength();
}
