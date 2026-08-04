package ug.co.targetfinance.bank2wallet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends Activity {
    private final int navy = Color.rgb(16, 43, 51);
    private final int deepNavy = Color.rgb(7, 25, 29);
    private final int teal = Color.rgb(36, 185, 154);
    private final int airtelRed = Color.rgb(224, 0, 0);
    private final int mtnYellow = Color.rgb(255, 204, 0);
    private final int muted = Color.rgb(92, 111, 122);
    private final int danger = Color.rgb(200, 35, 51);
    private final int border = Color.rgb(207, 222, 224);
    private final int pageBg = Color.rgb(241, 247, 247);

    private static final int MOBILE_TO_MOBILE = 1;
    private static final int MOBILE_TO_BANK = 2;
    private static final int BILL_PAYMENT = 3;
    private static final int PREMIUM_BILL_PAYMENT = 4;

    private static final FeeBand[] MTN_MOBILE_TO_MOBILE = {
            new FeeBand(500, 2500, 100), new FeeBand(2501, 5000, 100),
            new FeeBand(5001, 15000, 500), new FeeBand(15001, 30000, 500),
            new FeeBand(30001, 45000, 500), new FeeBand(45001, 60000, 500),
            new FeeBand(60001, 125000, 1000), new FeeBand(125001, 250000, 1000),
            new FeeBand(250001, 500000, 1000), new FeeBand(500001, 1000000, 1500),
            new FeeBand(1000001, 2000000, 2000), new FeeBand(2000001, 4000000, 2000),
            new FeeBand(4000001, 5000000, 2000)
    };

    private static final FeeBand[] MTN_MOBILE_TO_BANK = {
            new FeeBand(2501, 5000, 1500), new FeeBand(5001, 15000, 1500),
            new FeeBand(15001, 30000, 1500), new FeeBand(30001, 45000, 1500),
            new FeeBand(45001, 60000, 1500), new FeeBand(60001, 125000, 1500),
            new FeeBand(125001, 250000, 2250), new FeeBand(250001, 500000, 4100),
            new FeeBand(500001, 1000000, 6150), new FeeBand(1000001, 2000000, 9250),
            new FeeBand(2000001, 4000000, 11300), new FeeBand(4000001, 5000000, 11300)
    };

    private static final FeeBand[] MTN_BILL_PAYMENT = {
            new FeeBand(500, 2500, 110), new FeeBand(2501, 5000, 150),
            new FeeBand(5001, 15000, 550), new FeeBand(15001, 30000, 650),
            new FeeBand(30001, 45000, 750), new FeeBand(45001, 60000, 850),
            new FeeBand(60001, 125000, 950), new FeeBand(125001, 250000, 1050),
            new FeeBand(250001, 500000, 1300), new FeeBand(500001, 1000000, 3350),
            new FeeBand(1000001, 2000000, 5750), new FeeBand(2000001, 4000000, 5750),
            new FeeBand(4000001, 5000000, 5750)
    };

    private static final FeeBand[] MTN_PREMIUM_BILL_PAYMENT = {
            new FeeBand(500, 2500, 190), new FeeBand(2501, 5000, 600),
            new FeeBand(5001, 15000, 1000), new FeeBand(15001, 30000, 1600),
            new FeeBand(30001, 45000, 2100), new FeeBand(45001, 60000, 2800),
            new FeeBand(60001, 125000, 3700), new FeeBand(125001, 250000, 4150),
            new FeeBand(250001, 500000, 5300), new FeeBand(500001, 1000000, 6300),
            new FeeBand(1000001, 2000000, 6300), new FeeBand(2000001, 4000000, 6300),
            new FeeBand(4000001, 5000000, 6300)
    };

    private static final FeeBand[] AIRTEL_MOBILE_TO_MOBILE = {
            new FeeBand(500, 2500, 500), new FeeBand(2501, 5000, 500),
            new FeeBand(5001, 15000, 1000), new FeeBand(15001, 30000, 1000),
            new FeeBand(30001, 45000, 1100), new FeeBand(45001, 60000, 1100),
            new FeeBand(60001, 125000, 1400), new FeeBand(125001, 250000, 1400),
            new FeeBand(250001, 500000, 1400), new FeeBand(500001, 1000000, 2200),
            new FeeBand(1000001, 2000000, 2200), new FeeBand(2000001, 3000000, 2200),
            new FeeBand(3000001, 4000000, 2200), new FeeBand(4000001, 5000000, 2200)
    };

    private static final FeeBand[] AIRTEL_MOBILE_TO_BANK = {
            new FeeBand(5001, 15000, 700), new FeeBand(15001, 30000, 880),
            new FeeBand(30001, 45000, 1210), new FeeBand(45001, 60000, 1500),
            new FeeBand(60001, 125000, 1500), new FeeBand(125001, 250000, 2250),
            new FeeBand(250001, 500000, 4100), new FeeBand(500001, 1000000, 6150),
            new FeeBand(1000001, 2000000, 9250), new FeeBand(2000001, 3000000, 11300),
            new FeeBand(3000001, 4000000, 11300), new FeeBand(4000001, 5000000, 11300)
    };

    private static final FeeBand[] AIRTEL_BILL_PAYMENT = {
            new FeeBand(500, 2500, 120), new FeeBand(2501, 5000, 150),
            new FeeBand(5001, 15000, 550), new FeeBand(15001, 30000, 650),
            new FeeBand(30001, 45000, 750), new FeeBand(45001, 60000, 850),
            new FeeBand(60001, 125000, 950), new FeeBand(125001, 250000, 1050),
            new FeeBand(250001, 500000, 1300), new FeeBand(500001, 1000000, 3350),
            new FeeBand(1000001, 2000000, 5750), new FeeBand(2000001, 4000000, 5750),
            new FeeBand(4000001, 5000000, 5750)
    };

    private static final FeeBand[] AIRTEL_PREMIUM_BILL_PAYMENT = {
            new FeeBand(500, 2500, 190), new FeeBand(2501, 5000, 330),
            new FeeBand(5001, 15000, 1000), new FeeBand(15001, 30000, 1600),
            new FeeBand(30001, 45000, 2000), new FeeBand(45001, 60000, 2650),
            new FeeBand(60001, 125000, 3500), new FeeBand(125001, 250000, 3950),
            new FeeBand(250001, 500000, 5050), new FeeBand(500001, 1000000, 6300),
            new FeeBand(1000001, 2000000, 6300), new FeeBand(2000001, 4000000, 6300),
            new FeeBand(4000001, 5000000, 6300)
    };

    private RadioButton airtelOption;
    private RadioButton mtnOption;
    private RadioButton mobileOption;
    private RadioButton bankOption;
    private RadioButton billOption;
    private RadioButton premiumBillOption;
    private EditText walletInput;
    private EditText bankInput;
    private EditText amountInput;
    private Button carryButton;
    private TextView statusText;
    private TextView feeValue;
    private TextView walletValue;
    private TextView bankValue;
    private TextView totalDebitValue;
    private TextView bandValue;
    private TextView typeNoteValue;

    private long lastNewWallet = 0;
    private long lastNewBank = 0;
    private boolean hasValidResult = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildUi();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        );
        calculate();
        showDefaultKeyboard();
    }

    private void buildUi() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(pageBg);
        root.addView(buildHeader());

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(16), dp(18), dp(16), dp(18));
        scroll.addView(page, new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        LinearLayout form = card();
        TextView title = title("Mobile Money Fees");
        title.setPadding(0, 0, 0, dp(12));
        form.addView(title);

        RadioGroup providerGroup = choiceGroup(RadioGroup.HORIZONTAL);
        airtelOption = choice("Airtel", true);
        mtnOption = choice("MTN", false);
        providerGroup.addView(airtelOption, optionParams());
        providerGroup.addView(mtnOption, optionParams());
        providerGroup.setOnCheckedChangeListener((group, checkedId) -> {
            styleOptions();
            calculate();
        });
        form.addView(providerGroup, marginBottom(dp(12)));

        RadioGroup typeGroup = choiceGroup(RadioGroup.VERTICAL);
        mobileOption = choice("Mobile to Mobile", true);
        bankOption = choice("Mobile to Bank", false);
        billOption = choice("Bill Payment", false);
        premiumBillOption = choice("Premium Bill Payment", false);
        typeGroup.addView(mobileOption, tallOptionParams());
        typeGroup.addView(bankOption, tallOptionParams());
        typeGroup.addView(billOption, tallOptionParams());
        typeGroup.addView(premiumBillOption, tallOptionParams());
        typeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            styleOptions();
            calculate();
        });
        form.addView(typeGroup, marginBottom(dp(14)));

        walletInput = moneyInput("Wallet balance");
        bankInput = moneyInput("Bank balance");
        amountInput = moneyInput("Transaction amount");

        form.addView(field("Wallet Balance", walletInput));
        form.addView(field("Bank Balance", bankInput));
        form.addView(field("Transaction Amount", amountInput));

        statusText = new TextView(this);
        statusText.setTextColor(muted);
        statusText.setTextSize(13);
        statusText.setPadding(0, 0, 0, dp(10));
        form.addView(statusText);

        page.addView(form);
        page.addView(resultCard());

        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { calculate(); }
            @Override public void afterTextChanged(Editable s) {}
        };
        walletInput.addTextChangedListener(watcher);
        bankInput.addTextChangedListener(watcher);
        amountInput.addTextChangedListener(watcher);

        root.addView(scroll, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1
        ));
        setContentView(root);
        styleOptions();
    }

    private View buildHeader() {
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.VERTICAL);
        header.setPadding(dp(18), dp(20), dp(18), dp(18));
        header.setBackgroundColor(navy);

        TextView appName = new TextView(this);
        appName.setText("Mobile Money Fees");
        appName.setTextColor(Color.WHITE);
        appName.setTextSize(24);
        appName.setTypeface(Typeface.DEFAULT_BOLD);
        header.addView(appName);

        TextView subtitle = new TextView(this);
        subtitle.setText("MTN and Airtel tariff calculator");
        subtitle.setTextColor(Color.rgb(184, 219, 215));
        subtitle.setTextSize(13);
        subtitle.setPadding(0, dp(4), 0, 0);
        header.addView(subtitle);
        return header;
    }

    private View resultCard() {
        LinearLayout results = card();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, dp(14), 0, 0);
        results.setLayoutParams(params);

        TextView title = title("Result");
        title.setPadding(0, 0, 0, dp(10));
        results.addView(title);

        feeValue = resultRow(results, "Transaction Fee");
        totalDebitValue = resultRow(results, "Wallet Debit");
        walletValue = resultRow(results, "New Wallet Balance");
        bankValue = resultRow(results, "New Bank Balance");
        bandValue = resultRow(results, "Applied Band");
        typeNoteValue = resultRow(results, "Table Used");

        carryButton = new Button(this);
        carryButton.setText("Use Result as Next Transaction");
        carryButton.setAllCaps(false);
        carryButton.setTextSize(14);
        carryButton.setTypeface(Typeface.DEFAULT_BOLD);
        carryButton.setTextColor(Color.WHITE);
        carryButton.setBackground(makeRoundRect(teal, teal, dp(12)));
        carryButton.setOnClickListener(v -> carryBalances());
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(50)
        );
        buttonParams.setMargins(0, dp(12), 0, 0);
        results.addView(carryButton, buttonParams);
        return results;
    }

    private TextView resultRow(LinearLayout parent, String label) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, dp(6), 0, dp(6));

        TextView name = new TextView(this);
        name.setText(label);
        name.setTextColor(muted);
        name.setTextSize(13);
        row.addView(name, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        TextView value = new TextView(this);
        value.setText("-");
        value.setTextColor(deepNavy);
        value.setTextSize(15);
        value.setGravity(Gravity.END);
        value.setTypeface(Typeface.DEFAULT_BOLD);
        row.addView(value, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        parent.addView(row);
        return value;
    }

    private View field(String label, EditText input) {
        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setOrientation(LinearLayout.VERTICAL);
        wrapper.setLayoutParams(marginBottom(dp(12)));

        TextView labelView = new TextView(this);
        labelView.setText(label);
        labelView.setTextColor(deepNavy);
        labelView.setTextSize(13);
        labelView.setTypeface(Typeface.DEFAULT_BOLD);
        labelView.setPadding(0, 0, 0, dp(5));
        wrapper.addView(labelView);

        wrapper.addView(input, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(54)
        ));
        return wrapper;
    }

    private EditText moneyInput(String hint) {
        EditText input = new EditText(this);
        input.setHint(hint);
        input.setSingleLine(true);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setTextSize(16);
        input.setTextColor(deepNavy);
        input.setHintTextColor(muted);
        input.setPadding(dp(14), 0, dp(14), 0);
        input.setBackground(makeRoundRect(Color.rgb(247, 250, 250), border, dp(12)));
        input.setSelectAllOnFocus(true);
        return input;
    }

    private RadioGroup choiceGroup(int orientation) {
        RadioGroup group = new RadioGroup(this);
        group.setOrientation(orientation);
        group.setGravity(Gravity.CENTER);
        group.setBackground(makeRoundRect(Color.rgb(247, 250, 250), border, dp(12)));
        group.setPadding(dp(6), dp(6), dp(6), dp(6));
        return group;
    }

    private RadioButton choice(String text, boolean checked) {
        RadioButton button = new RadioButton(this);
        button.setText(text);
        button.setId(View.generateViewId());
        button.setTextSize(14);
        button.setTypeface(Typeface.DEFAULT_BOLD);
        button.setButtonDrawable(null);
        button.setGravity(Gravity.CENTER);
        button.setChecked(checked);
        return button;
    }

    private void styleOptions() {
        styleProviderOption(airtelOption, airtelOption.isChecked(), airtelRed, Color.WHITE);
        styleProviderOption(mtnOption, mtnOption.isChecked(), mtnYellow, deepNavy);
        styleTypeOption(mobileOption, mobileOption.isChecked());
        styleTypeOption(bankOption, bankOption.isChecked());
        styleTypeOption(billOption, billOption.isChecked());
        styleTypeOption(premiumBillOption, premiumBillOption.isChecked());
    }

    private void styleProviderOption(RadioButton button, boolean selected, int color, int selectedText) {
        button.setTextColor(selected ? selectedText : deepNavy);
        button.setBackground(makeRoundRect(
                selected ? color : Color.TRANSPARENT,
                selected ? color : Color.TRANSPARENT,
                dp(10)
        ));
    }

    private void styleTypeOption(RadioButton button, boolean selected) {
        button.setTextColor(selected ? Color.WHITE : deepNavy);
        button.setBackground(makeRoundRect(
                selected ? teal : Color.TRANSPARENT,
                selected ? teal : Color.TRANSPARENT,
                dp(10)
        ));
    }

    private void calculate() {
        if (statusText == null) return;

        long wallet = parseAmount(walletInput);
        long bank = parseAmount(bankInput);
        long amount = parseAmount(amountInput);
        FeeBand band = findBand(amount);

        hasValidResult = false;
        carryButton.setEnabled(false);
        carryButton.setAlpha(0.55f);

        if (amount <= 0) {
            showEmptyResult("Enter a transaction amount.");
            return;
        }
        if (band == null) {
            showEmptyResult("Amount is outside the selected tariff range.");
            statusText.setTextColor(danger);
            return;
        }

        long fee = band.fee;
        long totalDebit = amount + fee;
        long newWallet = wallet - totalDebit;
        long newBank = isBankTransfer() ? bank + amount : bank;

        lastNewWallet = newWallet;
        lastNewBank = newBank;
        hasValidResult = true;

        feeValue.setText(money(fee));
        totalDebitValue.setText(money(totalDebit));
        walletValue.setText(money(newWallet));
        bankValue.setText(isBankTransfer() ? money(newBank) : "Not affected");
        bandValue.setText(formatPlain(band.min) + " - " + formatPlain(band.max));
        typeNoteValue.setText(selectedProvider() + " / " + selectedTransactionName());

        if (newWallet < 0) {
            statusText.setText("Insufficient wallet balance after fee.");
            statusText.setTextColor(danger);
        } else {
            statusText.setText("Fee applied from " + selectedProvider() + " " + selectedTransactionName() + " table.");
            statusText.setTextColor(selectedProvider().equals("MTN") ? Color.rgb(136, 102, 0) : airtelRed);
        }

        carryButton.setEnabled(true);
        carryButton.setAlpha(1f);
    }

    private void showEmptyResult(String message) {
        feeValue.setText("UGX 0");
        totalDebitValue.setText("UGX 0");
        walletValue.setText("UGX 0");
        bankValue.setText(isBankTransfer() ? "UGX 0" : "Not affected");
        bandValue.setText("-");
        typeNoteValue.setText(selectedProvider() + " / " + selectedTransactionName());
        statusText.setText(message);
        statusText.setTextColor(muted);
    }

    private FeeBand findBand(long amount) {
        FeeBand[] bands = selectedBands();
        for (FeeBand band : bands) {
            if (amount >= band.min && amount <= band.max) {
                return band;
            }
        }
        return null;
    }

    private FeeBand[] selectedBands() {
        boolean mtn = selectedProvider().equals("MTN");
        int type = selectedTransactionType();
        if (type == MOBILE_TO_BANK) return mtn ? MTN_MOBILE_TO_BANK : AIRTEL_MOBILE_TO_BANK;
        if (type == BILL_PAYMENT) return mtn ? MTN_BILL_PAYMENT : AIRTEL_BILL_PAYMENT;
        if (type == PREMIUM_BILL_PAYMENT) return mtn ? MTN_PREMIUM_BILL_PAYMENT : AIRTEL_PREMIUM_BILL_PAYMENT;
        return mtn ? MTN_MOBILE_TO_MOBILE : AIRTEL_MOBILE_TO_MOBILE;
    }

    private int selectedTransactionType() {
        if (bankOption != null && bankOption.isChecked()) return MOBILE_TO_BANK;
        if (billOption != null && billOption.isChecked()) return BILL_PAYMENT;
        if (premiumBillOption != null && premiumBillOption.isChecked()) return PREMIUM_BILL_PAYMENT;
        return MOBILE_TO_MOBILE;
    }

    private String selectedTransactionName() {
        int type = selectedTransactionType();
        if (type == MOBILE_TO_BANK) return "Mobile to Bank";
        if (type == BILL_PAYMENT) return "Bill Payment";
        if (type == PREMIUM_BILL_PAYMENT) return "Premium Bill Payment";
        return "Mobile to Mobile";
    }

    private boolean isBankTransfer() {
        return selectedTransactionType() == MOBILE_TO_BANK;
    }

    private String selectedProvider() {
        return mtnOption != null && mtnOption.isChecked() ? "MTN" : "Airtel";
    }

    private void carryBalances() {
        if (!hasValidResult) return;
        walletInput.setText(formatPlain(lastNewWallet));
        bankInput.setText(formatPlain(lastNewBank));
        amountInput.setText("");
        amountInput.requestFocus();
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (keyboard != null) {
            keyboard.showSoftInput(amountInput, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private long parseAmount(EditText input) {
        if (input == null) return 0;
        String digits = input.getText().toString().replaceAll("[^0-9]", "");
        if (digits.isEmpty()) return 0;
        try {
            return Long.parseLong(digits);
        } catch (NumberFormatException ignored) {
            return 0;
        }
    }

    private String money(long amount) {
        return "UGX " + formatPlain(amount);
    }

    private String formatPlain(long amount) {
        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }

    private LinearLayout card() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(18), dp(18), dp(18), dp(18));
        card.setBackground(makeRoundRect(Color.WHITE, Color.rgb(229, 238, 239), dp(16)));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            card.setElevation(dp(2));
        }
        card.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return card;
    }

    private TextView title(String text) {
        TextView view = new TextView(this);
        view.setText(text);
        view.setTextColor(deepNavy);
        view.setTextSize(18);
        view.setTypeface(Typeface.DEFAULT_BOLD);
        return view;
    }

    private LinearLayout.LayoutParams optionParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, dp(42), 1);
        params.setMargins(dp(3), 0, dp(3), 0);
        return params;
    }

    private LinearLayout.LayoutParams tallOptionParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(42)
        );
        params.setMargins(0, dp(2), 0, dp(2));
        return params;
    }

    private LinearLayout.LayoutParams marginBottom(int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, bottom);
        return params;
    }

    private GradientDrawable makeRoundRect(int fill, int stroke, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(fill);
        drawable.setCornerRadius(radius);
        if (stroke != Color.TRANSPARENT) {
            drawable.setStroke(dp(1), stroke);
        }
        return drawable;
    }

    private void showDefaultKeyboard() {
        if (amountInput == null) return;
        amountInput.postDelayed(() -> {
            amountInput.requestFocus();
            InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (keyboard != null) {
                keyboard.showSoftInput(amountInput, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 250);
    }

    private int dp(int value) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(value * density);
    }

    private static class FeeBand {
        final long min;
        final long max;
        final long fee;

        FeeBand(long min, long max, long fee) {
            this.min = min;
            this.max = max;
            this.fee = fee;
        }
    }
}
