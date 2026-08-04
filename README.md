# Bank 2 Wallet

Native Android calculator for mobile money wallet-to-bank transfers.

The app embeds the MTN and Airtel fee bands from:

```text
D:\Bank 2 Wallet\QQQ_WALLET TO BANK RATES 2025_VLOOK.xlsx
```

## Features

- Select Airtel or MTN.
- Enter wallet balance, bank balance, and transfer amount.
- Automatically applies the matching transfer fee band.
- Shows wallet debit, new wallet balance, and new bank balance.
- Carry the result forward as the starting balance for the next transaction.

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
