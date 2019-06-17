# GlideUtil

Glide for Android

## Step 1. Add it in your root build.gradle at the end of repositories

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

## Step 2. Add the dependency

```
dependencies {
        implementation 'com.github.zeropercenthappy:GlideUtil:1.0.1'
}
```

## Usage

### kotlin

```kotlin
loadImage {
    this.uri = url
    this.imageView = iv
    // options
//    this.placeHolder = holder
//    this.errorHolder = holder
//    this.circleCrop = true
}
```

### java

```java
GlideUtils.loadImage();
```

or (**not recommend**)

```java
GlideDSLKt.loadImage(new Function1<GlideWrapper, Unit>() {
    @Override
    public Unit invoke(GlideWrapper glideWrapper) {
        glideWrapper.setUri();
        glideWrapper.setImageView();
        glideWrapper.setPlaceHolder();
        glideWrapper.setErrorHolder();
        glideWrapper.setCircleCrop();
        return null;
    }
});
```

