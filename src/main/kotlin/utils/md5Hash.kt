package utils

import java.security.MessageDigest

fun String.md5Hash() = MessageDigest.getInstance("MD5").digest(this.toByteArray(Charsets.UTF_8))

@ExperimentalUnsignedTypes
fun String.md5HashString() = MessageDigest.getInstance("MD5").digest(this.toByteArray(Charsets.UTF_8)).joinToString("") { it.toUByte().toString(16).padStart(2, '0') }