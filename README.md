# CurrencyPick for Android
![](https://img.shields.io/badge/kotlin-1.0.0-blueviolet) [![](https://img.shields.io/badge/paypal-donate-yellow.svg)](https://www.paypal.com/donate/?hosted_button_id=EATW6JWV2AQ4N)

CurrencyPick is a simple android library that can be show a currency picker. Inspired by https://github.com/Scrounger/CountryCurrencyPicker

<img src="https://github.com/BrunoRCE/CurrencyPick/blob/main/assets/screen3.png" width="60%">


## How to use

### Integration

Integrating the project is simple a refined all you need to do is follow the below steps

Step 1\. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```java
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```

Step 2\. Add the dependency

```java
dependencies {
      implementation 'com.github.brunorce:currencypick:1.0.0'
}
```

### Usage

Once the project has been added to gradle, the dialog can be easily used.

```java
      CurrencyPick { currency ->
            // Implement your code here
        }.show(supportFragmentManager, "")
```

### Custom query hint

Add your custom query hint with the ***setQueryHint*** method

```java
     val currencyPick = CurrencyPick { currency ->
              // Implement your code here
        }

        currencyPick.setQueryHint("Custom hint...")
```

### Show minimal mode

Show only the minimum information with the ***showMinimalMode*** method

```java
     val currencyPick = CurrencyPick { currency ->
              // Implement your code here
        }

        currencyPick.showMinimalMode()
```

<img src="https://github.com/BrunoRCE/CurrencyPick/blob/main/assets/screen1.png" width="60%">

### Change the text color

Change the color of the texts with the ***setTextColor*** method

```java
     val currencyPick = CurrencyPick { currency ->
              // Implement your code here
        }

        currencyPick.setTextColor("#2196F3")
```
<img src="https://github.com/BrunoRCE/CurrencyPick/blob/main/assets/screen2.png" width="40%">
<img src="https://github.com/BrunoRCE/CurrencyPick/blob/main/assets/screen4.png" width="40%">
<img src="https://github.com/BrunoRCE/CurrencyPick/blob/main/assets/screen5.png" width="40%">


