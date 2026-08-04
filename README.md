# Tariff Desk UG

Native Android calculator for Uganda mobile money transaction fees.

The app embeds MTN and Airtel fee bands for common transaction types.

Current transaction options:

- Mobile to Mobile
- Mobile to Bank
- Cash Withdrawal
- Bill Payment
- Premium Bill Payment

Provider and transaction type are selected from dropdown lists. Amount inputs
format with thousands separators while typing.

Use Balance Mode to switch between quick fee checks, wallet balance tracking, and
wallet-plus-bank tracking for Mobile to Bank transactions. Balances are optional:
the app shows before/after balances only when the matching starting balance is entered.

For Cash Withdrawal, the app adds the withdrawal fee and 0.5% withdrawal tax.
Wallet before/after tracking is available through Balance Mode, just like the
other transaction types.

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
