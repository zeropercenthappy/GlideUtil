# GlideUtil

Glide for Android

### Step 1. Add it in your root build.gradle at the end of repositories

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

### Step 2. Add the dependency

```groovy
dependencies {
        // For android compat version
        implementation 'com.github.zeropercenthappy:GlideUtil:1.0.4'
        // For androidX version
        implementation 'com.github.zeropercenthappy:GlideUtil:1.1.0'
}
```

## Usage

### load image into a ImageView:

```kotlin
loadImage {
    // required
    imageView = ...
    url = ...
    // options
    requestOptions = ...
    transitionOptions = ...
    thumbnail = ...
    errorBuilder = ...
    requestListener = ...
}
```

or

```kotlin
loadImageWithBitmap {
    // required
    imageView = ...
    url = ...
    // options
    requestOptions = ...
    transitionOptions = ...
    thumbnail = ...
    errorBuilder = ...
    requestListener = ...
}
```

### download image file:

```kotlin
// it's a suspend function, should be called only from a coroutine or another suspend function.
val result = context.downloadImage {
    // required
    url = ...
    storageFile = ...
    // options
    requestListener = ...
}
```

