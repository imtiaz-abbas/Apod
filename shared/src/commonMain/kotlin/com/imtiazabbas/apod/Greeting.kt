package com.imtiazabbas.apod

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}