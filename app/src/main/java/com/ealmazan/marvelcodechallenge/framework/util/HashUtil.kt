package com.ealmazan.marvelcodechallenge.framework.util

import java.security.MessageDigest

object HashUtil {

    fun hash(input: String, algorithm: String): String? {
        val md: MessageDigest = MessageDigest.getInstance(algorithm)
        md.update(input.toByteArray())
        val digest: ByteArray = md.digest()
        val hexString = StringBuilder()
        for (aMessageDigest in digest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()
    }
}