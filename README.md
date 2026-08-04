# Mobile Money Fees

Native Android calculator for Uganda mobile money transaction fees.

The app embeds MTN and Airtel fee bands for common transaction types.

Current transaction options:

- Mobile to Mobile
- Mobile to Bank
- Bill Payment
- Premium Bill Payment

Provider colors:

- Airtel: red
- MTN: yellow

The first version was based on `D:\Bank 2 Wallet\QQQ_WALLET TO BANK RATES 2025_VLOOK.xlsx`; this version expands it with published MTN and Airtel tariff categories.

## Build

Open this folder in Android Studio:

```text
D:\Bank 2 Wallet\Bank2WalletApp
```

Then build the debug APK from Android Studio, or run:

```text
gradle assembleDebug
```

The APK output will be under:

```text
app\build\outputs\apk\debug\
```
