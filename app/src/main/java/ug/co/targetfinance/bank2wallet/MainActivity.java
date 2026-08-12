package ug.co.targetfinance.bank2wallet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.HideReturnsTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends Activity {
    private final int navy = Color.rgb(16, 43, 51);
    private final int deepNavy = Color.rgb(7, 25, 29);
    private final int teal = Color.rgb(36, 185, 154);
    private final int mint = Color.rgb(222, 248, 242);
    private final int airtelRed = Color.rgb(224, 0, 0);
    private final int mtnYellow = Color.rgb(255, 204, 0);
    private final int muted = Color.rgb(92, 111, 122);
    private final int danger = Color.rgb(200, 35, 51);
    private final int border = Color.rgb(207, 222, 224);
    private final int pageBg = Color.rgb(235, 244, 243);
    private final int softPanel = Color.rgb(247, 251, 251);

    private static final int MOBILE_TO_MOBILE = 1;
    private static final int MOBILE_TO_BANK = 2;
    private static final int BILL_PAYMENT = 3;
    private static final int PREMIUM_BILL_PAYMENT = 4;
    private static final int CASH_WITHDRAWAL = 5;
    private static final String[] PROVIDERS = {"Airtel Money", "MTN MoMo"};
    private static final String[] TRANSACTION_TYPES = {
            "Mobile to Mobile",
            "Mobile to Bank",
            "Cash Withdrawal",
            "Bill Payment",
            "Premium Bill Payment"
    };
    private static final String[] BALANCE_MODES = {
            "Quick fees",
            "Wallet only",
            "Wallet + bank"
    };

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

    private static final FeeBand[] MTN_CASH_WITHDRAWAL = {
            new FeeBand(500, 2500, 330, 3, 13),
            new FeeBand(2501, 5000, 440, 13, 25),
            new FeeBand(5001, 15000, 700, 25, 75),
            new FeeBand(15001, 30000, 880, 75, 150),
            new FeeBand(30001, 45000, 1210, 150, 225),
            new FeeBand(45001, 60000, 1500, 225, 300),
            new FeeBand(60001, 125000, 1925, 300, 625),
            new FeeBand(125001, 250000, 3575, 625, 1250),
            new FeeBand(250001, 500000, 7000, 1250, 2500),
            new FeeBand(500001, 1000000, 12500, 2500, 5000),
            new FeeBand(1000001, 2000000, 15000, 5000, 10000),
            new FeeBand(2000001, 4000000, 18000, 10000, 20000),
            new FeeBand(4000001, 5000000, 20000, 20000, 35000)
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
            new FeeBand(500, 2500, 100), new FeeBand(2501, 5000, 100),
            new FeeBand(5001, 15000, 500), new FeeBand(15001, 30000, 500),
            new FeeBand(30001, 45000, 500), new FeeBand(45001, 60000, 500),
            new FeeBand(60001, 125000, 1000), new FeeBand(125001, 250000, 1000),
            new FeeBand(250001, 500000, 1000), new FeeBand(500001, 1000000, 1500),
            new FeeBand(1000001, 2000000, 2000), new FeeBand(2000001, 3000000, 2000),
            new FeeBand(3000001, 4000000, 2000), new FeeBand(4000001, 5000000, 2000)
    };

    private static final FeeBand[] AIRTEL_MOBILE_TO_BANK = {
            new FeeBand(5001, 15000, 700), new FeeBand(15001, 30000, 880),
            new FeeBand(30001, 45000, 1210), new FeeBand(45001, 60000, 1500),
            new FeeBand(60001, 125000, 1500), new FeeBand(125001, 250000, 2250),
            new FeeBand(250001, 500000, 4100), new FeeBand(500001, 1000000, 6150),
            new FeeBand(1000001, 2000000, 9250), new FeeBand(2000001, 3000000, 11300),
            new FeeBand(3000001, 4000000, 11300), new FeeBand(4000001, 5000000, 11300)
    };

    private static final FeeBand[] AIRTEL_CASH_WITHDRAWAL = {
            new FeeBand(500, 2500, 330, 0, 13),
            new FeeBand(2501, 5000, 440, 13, 25),
            new FeeBand(5001, 15000, 700, 25, 75),
            new FeeBand(15001, 30000, 880, 75, 150),
            new FeeBand(30001, 45000, 1210, 150, 225),
            new FeeBand(45001, 60000, 1500, 225, 300),
            new FeeBand(60001, 125000, 1925, 300, 625),
            new FeeBand(125001, 250000, 3575, 625, 1250),
            new FeeBand(250001, 500000, 7000, 1250, 2500),
            new FeeBand(500001, 1000000, 12500, 2500, 5000),
            new FeeBand(1000001, 2000000, 15000, 5000, 10000),
            new FeeBand(2000001, 3000000, 18000, 10000, 15000),
            new FeeBand(3000001, 4000000, 18000, 15000, 20000),
            new FeeBand(4000001, 5000000, 18000, 20000, 25000)
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

    private Spinner providerSpinner;
    private Spinner transactionTypeSpinner;
    private Spinner balanceModeSpinner;
    private LinearLayout balanceSection;
    private View walletField;
    private View bankField;
    private EditText walletInput;
    private EditText bankInput;
    private EditText amountInput;
    private TextView statusText;
    private TextView feeValue;
    private TextView taxValue;
    private LinearLayout taxRow;
    private TextView deductionsValue;
    private LinearLayout deductionsRow;
    private TextView walletValue;
    private TextView bankValue;
    private LinearLayout bankResultRow;
    private TextView totalDebitValue;
    private TextView splitSaveButton;
    private SplitOption currentSplitOption;

    private boolean formattingAmounts = false;
    private boolean updatingBalanceModes = false;
    private boolean pendingCommaBackspace = false;
    private int pendingCommaBackspaceDigitIndex = -1;

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

        providerSpinner = dropdown(PROVIDERS);
        transactionTypeSpinner = dropdown(TRANSACTION_TYPES);
        balanceModeSpinner = dropdown(BALANCE_MODES);
        root.addView(buildHeader());

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(14), dp(14), dp(14), dp(18));
        scroll.addView(page, new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        LinearLayout form = card();
        TextView title = title("Transaction Setup");
        title.setPadding(0, 0, 0, dp(4));
        form.addView(title);

        TextView prompt = smallText("Enter an amount to preview the exact debit.");
        prompt.setPadding(0, 0, 0, dp(14));
        form.addView(prompt);

        form.addView(dropdownField("Tracking Option", balanceModeSpinner));

        walletInput = moneyInput("Wallet balance before");
        bankInput = moneyInput("Bank balance before");
        amountInput = moneyInput("Transaction amount");

        balanceSection = new LinearLayout(this);
        balanceSection.setOrientation(LinearLayout.VERTICAL);
        walletField = field("Wallet Balance Before", walletInput);
        bankField = field("Bank Balance Before", bankInput);
        balanceSection.addView(walletField);
        balanceSection.addView(bankField);

        form.addView(field("Transaction Amount", amountInput));
        form.addView(balanceSection);

        statusText = new TextView(this);
        statusText.setTextColor(muted);
        statusText.setTextSize(13);
        statusText.setPadding(0, 0, 0, dp(10));
        form.addView(statusText);

        page.addView(form);
        page.addView(resultCard());

        TextWatcher watcher = formattedAmountWatcher();
        walletInput.addTextChangedListener(watcher);
        bankInput.addTextChangedListener(watcher);
        amountInput.addTextChangedListener(watcher);

        AdapterView.OnItemSelectedListener dropdownListener = new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent == transactionTypeSpinner) {
                    refreshBalanceModes();
                }
                updateDynamicSections();
                calculate();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        };
        providerSpinner.setOnItemSelectedListener(dropdownListener);
        transactionTypeSpinner.setOnItemSelectedListener(dropdownListener);
        balanceModeSpinner.setOnItemSelectedListener(dropdownListener);

        root.addView(scroll, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1
        ));
        setContentView(root);
        refreshBalanceModes();
        updateDynamicSections();
    }

    private View buildHeader() {
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.VERTICAL);
        header.setPadding(dp(18), dp(22), dp(18), dp(18));
        header.setBackgroundColor(navy);

        LinearLayout titleRow = new LinearLayout(this);
        titleRow.setOrientation(LinearLayout.HORIZONTAL);
        titleRow.setGravity(Gravity.CENTER_VERTICAL);

        TextView appName = new TextView(this);
        appName.setText("Tariff Desk UG");
        appName.setTextColor(Color.WHITE);
        appName.setTextSize(25);
        appName.setTypeface(Typeface.DEFAULT_BOLD);
        titleRow.addView(appName, new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1
        ));

        TextView helpButton = new TextView(this);
        helpButton.setText("?");
        helpButton.setTextColor(Color.WHITE);
        helpButton.setTextSize(20);
        helpButton.setGravity(Gravity.CENTER);
        helpButton.setTypeface(Typeface.DEFAULT_BOLD);
        helpButton.setContentDescription("Help");
        helpButton.setBackground(makeRoundRect(Color.TRANSPARENT, Color.rgb(184, 219, 215), dp(18)));
        helpButton.setOnClickListener(v -> showHelpDialog());
        titleRow.addView(helpButton, new LinearLayout.LayoutParams(dp(36), dp(36)));
        header.addView(titleRow);

        TextView subtitle = new TextView(this);
        subtitle.setText("Fast fee checks for Airtel Money and MTN MoMo");
        subtitle.setTextColor(Color.rgb(184, 219, 215));
        subtitle.setTextSize(13);
        subtitle.setPadding(0, dp(4), 0, 0);
        header.addView(subtitle);

        LinearLayout headerControls = new LinearLayout(this);
        headerControls.setOrientation(LinearLayout.HORIZONTAL);
        headerControls.setPadding(0, dp(14), 0, 0);
        LinearLayout.LayoutParams providerParams = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.44f
        );
        providerParams.setMargins(0, 0, dp(8), 0);

        headerControls.addView(compactDropdownField("Service Provider", providerSpinner), providerParams);
        headerControls.addView(compactDropdownField("Transaction Type", transactionTypeSpinner), new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.56f
        ));
        header.addView(headerControls);
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

        TextView title = title("Cost Preview");
        title.setPadding(0, 0, 0, dp(4));
        results.addView(title);

        TextView caption = smallText("The amount below is what leaves the wallet.");
        caption.setPadding(0, 0, 0, dp(12));
        results.addView(caption);

        totalDebitValue = heroResult(results);
        feeValue = resultRow(results, "Transaction Fee");
        taxRow = resultRowContainer(results, "Withdraw Tax");
        taxValue = (TextView) taxRow.getTag();
        deductionsRow = resultRowContainer(results, "Total Deductions");
        deductionsValue = (TextView) deductionsRow.getTag();
        walletValue = resultRow(results, "Wallet Balance After");
        bankResultRow = resultRowContainer(results, "Bank Balance After");
        bankValue = (TextView) bankResultRow.getTag();
        splitSaveButton = splitSaveButton();
        results.addView(splitSaveButton);
        return results;
    }

    private void showHelpDialog() {
        ScrollView scroller = new ScrollView(this);
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(22), dp(12), dp(22), dp(6));
        scroller.addView(content);

        addHelpSection(content, "Service Provider");
        addHelpOption(content, "Airtel Money", "Uses Airtel Money tariff bands for the selected transaction type.");
        addHelpOption(content, "MTN MoMo", "Uses MTN MoMo tariff bands for the selected transaction type.");

        addHelpSection(content, "Transaction Type");
        addHelpOption(content, "Mobile to Mobile", "Sending money to another mobile money wallet.");
        addHelpOption(content, "Mobile to Bank", "Sending wallet money to a bank account.");
        addHelpOption(content, "Cash Withdrawal", "Shows fee, withdraw tax, and Total Deductions.");
        addHelpOption(content, "Bill Payment", "Use the lower bill-payment tariff column for the selected service provider.");
        addHelpOption(content, "Premium Bill Payment", "Use the higher bill-payment tariff column for the selected service provider.");

        addHelpSection(content, "Tracking Option");
        addHelpOption(content, "Quick fees", "Shows the transaction fee and wallet debit without tracking balances.");
        addHelpOption(content, "Wallet only", "Enter wallet balance before; the app shows the wallet balance after the transaction.");
        addHelpOption(content, "Wallet + bank", "For Mobile to Bank only; shows both wallet after and bank balance after.");

        addHelpSection(content, "Note");
        addHelpOption(content, "Billers", "MTN MoMo and Airtel Money group billers differently, so choose based on the selected service provider's tariff list.");

        new AlertDialog.Builder(this)
                .setTitle("Help")
                .setView(scroller)
                .setPositiveButton("Got it", null)
                .show();
    }

    private void addHelpSection(LinearLayout parent, String heading) {
        TextView headingView = new TextView(this);
        headingView.setText(heading);
        headingView.setTextColor(deepNavy);
        headingView.setTextSize(16);
        headingView.setTypeface(Typeface.DEFAULT_BOLD);
        headingView.setPadding(0, dp(14), 0, dp(5));
        parent.addView(headingView);
    }

    private void addHelpOption(LinearLayout parent, String option, String detail) {
        TextView optionView = new TextView(this);
        optionView.setText(option);
        optionView.setTextColor(deepNavy);
        optionView.setTextSize(14);
        optionView.setTypeface(Typeface.DEFAULT_BOLD);
        optionView.setPadding(0, dp(6), 0, dp(1));
        parent.addView(optionView);

        TextView detailView = smallText(detail);
        detailView.setTextSize(13);
        detailView.setLineSpacing(dp(2), 1.0f);
        detailView.setPadding(0, 0, 0, dp(4));
        parent.addView(detailView);
    }

    private TextView heroResult(LinearLayout parent) {
        LinearLayout panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(dp(14), dp(12), dp(14), dp(12));
        panel.setBackground(makeRoundRect(mint, Color.rgb(180, 228, 217), dp(8)));
        parent.addView(panel, marginBottom(dp(10)));

        TextView label = new TextView(this);
        label.setText("Wallet Debit");
        label.setTextColor(Color.rgb(38, 93, 84));
        label.setTextSize(12);
        label.setTypeface(Typeface.DEFAULT_BOLD);
        panel.addView(label);

        TextView value = new TextView(this);
        value.setText("UGX 0");
        value.setTextColor(deepNavy);
        value.setTextSize(26);
        value.setTypeface(Typeface.DEFAULT_BOLD);
        value.setPadding(0, dp(3), 0, 0);
        panel.addView(value);
        return value;
    }

    private TextView resultRow(LinearLayout parent, String label) {
        LinearLayout row = resultRowContainer(parent, label);
        return (TextView) row.getTag();
    }

    private LinearLayout resultRowContainer(LinearLayout parent, String label) {
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

        row.setTag(value);
        parent.addView(row);
        return row;
    }

    private TextView splitSaveButton() {
        TextView button = new TextView(this);
        button.setTextColor(deepNavy);
        button.setTextSize(15);
        button.setGravity(Gravity.CENTER);
        button.setTypeface(Typeface.DEFAULT_BOLD);
        button.setPadding(dp(12), dp(11), dp(12), dp(11));
        button.setBackground(makeRoundRect(Color.rgb(229, 252, 239), Color.rgb(103, 201, 143), dp(9)));
        button.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = marginBottom(0);
        params.setMargins(0, dp(10), 0, 0);
        button.setLayoutParams(params);
        button.setOnClickListener(v -> showSplitOptionDialog());
        return button;
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

        FrameLayout inputFrame = new FrameLayout(this);
        inputFrame.setBackground(makeRoundRect(softPanel, border, dp(10)));

        input.setBackgroundColor(Color.TRANSPARENT);
        input.setPadding(dp(14), 0, dp(44), 0);
        inputFrame.addView(input, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(54)
        ));

        TextView clearButton = clearInputButton(input);
        FrameLayout.LayoutParams clearParams = new FrameLayout.LayoutParams(dp(42), dp(54));
        clearParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        inputFrame.addView(clearButton, clearParams);

        input.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                clearButton.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });

        wrapper.addView(inputFrame, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(54)
        ));
        return wrapper;
    }

    private EditText moneyInput(String hint) {
        EditText input = new EditText(this);
        input.setHint(hint);
        input.setSingleLine(true);
        input.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        input.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        input.setTextSize(16);
        input.setTextColor(deepNavy);
        input.setHintTextColor(muted);
        input.setPadding(dp(14), 0, dp(44), 0);
        input.setBackgroundColor(Color.TRANSPARENT);
        input.setSelectAllOnFocus(true);
        return input;
    }

    private TextView clearInputButton(EditText input) {
        TextView clearButton = new TextView(this);
        clearButton.setText("X");
        clearButton.setTextColor(muted);
        clearButton.setTextSize(14);
        clearButton.setGravity(Gravity.CENTER);
        clearButton.setTypeface(Typeface.DEFAULT_BOLD);
        clearButton.setContentDescription("Clear field");
        clearButton.setVisibility(input.getText().length() > 0 ? View.VISIBLE : View.GONE);
        clearButton.setOnClickListener(v -> {
            input.setText("");
            input.requestFocus();
            calculate();
        });
        return clearButton;
    }

    private Spinner dropdown(String[] values) {
        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                values
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPadding(dp(10), 0, dp(10), 0);
        spinner.setBackground(makeRoundRect(softPanel, border, dp(10)));
        return spinner;
    }

    private TextView smallText(String text) {
        TextView view = new TextView(this);
        view.setText(text);
        view.setTextColor(muted);
        view.setTextSize(13);
        return view;
    }

    private View compactDropdownField(String label, Spinner spinner) {
        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setOrientation(LinearLayout.VERTICAL);

        TextView labelView = new TextView(this);
        labelView.setText(label);
        labelView.setTextColor(Color.rgb(184, 219, 215));
        labelView.setTextSize(11);
        labelView.setTypeface(Typeface.DEFAULT_BOLD);
        labelView.setPadding(0, 0, 0, dp(4));
        wrapper.addView(labelView);

        wrapper.addView(spinner, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(46)
        ));
        return wrapper;
    }

    private View dropdownField(String label, Spinner spinner) {
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

        wrapper.addView(spinner, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(54)
        ));
        return wrapper;
    }

    private TextWatcher formattedAmountWatcher() {
        return new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                pendingCommaBackspace = false;
                pendingCommaBackspaceDigitIndex = -1;
                if (formattingAmounts || count <= after || count != 1 || start < 0 || start >= s.length()) {
                    return;
                }
                if (s.charAt(start) == ',') {
                    int digitsBeforeComma = countDigitsBefore(s.toString(), start);
                    if (digitsBeforeComma > 0) {
                        pendingCommaBackspace = true;
                        pendingCommaBackspaceDigitIndex = digitsBeforeComma - 1;
                    }
                }
            }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                if (formattingAmounts) return;

                formattingAmounts = true;
                EditText activeInput = getCurrentFocus() instanceof EditText ? (EditText) getCurrentFocus() : null;
                if (activeInput != null && activeInput.getText() == s) {
                    int digitCursor = countDigitsBefore(activeInput.getText().toString(), activeInput.getSelectionStart());
                    String digits = digitsOnly(activeInput);
                    if (pendingCommaBackspace && pendingCommaBackspaceDigitIndex >= 0 && pendingCommaBackspaceDigitIndex < digits.length()) {
                        digits = digits.substring(0, pendingCommaBackspaceDigitIndex)
                                + digits.substring(pendingCommaBackspaceDigitIndex + 1);
                        digitCursor = pendingCommaBackspaceDigitIndex;
                    }
                    String formatted = digits.isEmpty() ? "" : formatPlain(parseDigits(digits));
                    if (!formatted.equals(activeInput.getText().toString())) {
                        activeInput.setText(formatted);
                        activeInput.setSelection(cursorForDigitPosition(formatted, digitCursor));
                    }
                }
                pendingCommaBackspace = false;
                pendingCommaBackspaceDigitIndex = -1;
                formattingAmounts = false;
                calculate();
            }
        };
    }

    private int countDigitsBefore(String value, int cursor) {
        int safeCursor = Math.max(0, Math.min(cursor, value.length()));
        int count = 0;
        for (int i = 0; i < safeCursor; i++) {
            if (Character.isDigit(value.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    private int cursorForDigitPosition(String value, int digitPosition) {
        if (digitPosition <= 0) return 0;
        int seen = 0;
        for (int i = 0; i < value.length(); i++) {
            if (Character.isDigit(value.charAt(i))) {
                seen++;
                if (seen == digitPosition) {
                    return i + 1;
                }
            }
        }
        return value.length();
    }

    private void updateDynamicSections() {
        if (updatingBalanceModes) return;
        boolean tracksWallet = tracksWalletBalance();
        boolean tracksBank = tracksBankBalance();
        if (balanceSection != null) {
            balanceSection.setVisibility(tracksWallet || tracksBank ? View.VISIBLE : View.GONE);
        }
        if (walletField != null) {
            walletField.setVisibility(tracksWallet ? View.VISIBLE : View.GONE);
        }
        if (bankField != null) {
            bankField.setVisibility(tracksBank ? View.VISIBLE : View.GONE);
        }
        if (taxRow != null) {
            taxRow.setVisibility(isCashWithdrawal() ? View.VISIBLE : View.GONE);
        }
        if (deductionsRow != null) {
            deductionsRow.setVisibility(isCashWithdrawal() ? View.VISIBLE : View.GONE);
        }
        if (walletValue != null) {
            View walletRow = (View) walletValue.getParent();
            walletRow.setVisibility(tracksWallet ? View.VISIBLE : View.GONE);
        }
        if (bankResultRow != null) {
            bankResultRow.setVisibility(tracksBank ? View.VISIBLE : View.GONE);
        }
        updateProviderAccent();
    }

    private void refreshBalanceModes() {
        if (balanceModeSpinner == null || updatingBalanceModes) return;

        updatingBalanceModes = true;
        String[] modes = isBankTransfer()
                ? BALANCE_MODES
                : new String[]{"Quick fees", "Wallet only"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                modes
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        balanceModeSpinner.setAdapter(adapter);

        int selection = isBankTransfer() ? indexOf(modes, "Wallet + bank") : indexOf(modes, "Quick fees");
        balanceModeSpinner.setSelection(selection);
        updatingBalanceModes = false;
    }

    private void updateProviderAccent() {
        int accent = selectedProvider().equals("MTN") ? mtnYellow : airtelRed;
        int text = selectedProvider().equals("MTN") ? deepNavy : Color.WHITE;
        if (providerSpinner != null) {
            providerSpinner.setBackground(makeRoundRect(accent, accent, dp(10)));
            tintSelectedSpinnerText(providerSpinner, text);
        }
        if (transactionTypeSpinner != null) {
            transactionTypeSpinner.setBackground(makeRoundRect(Color.rgb(22, 65, 72), Color.rgb(48, 110, 119), dp(10)));
            tintSelectedSpinnerText(transactionTypeSpinner, Color.WHITE);
        }
        if (statusText != null) {
            statusText.setTextColor(text == Color.WHITE ? airtelRed : Color.rgb(136, 102, 0));
        }
    }

    private void tintSelectedSpinnerText(Spinner spinner, int color) {
        View selected = spinner.getSelectedView();
        if (selected instanceof TextView) {
            ((TextView) selected).setTextColor(color);
            ((TextView) selected).setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    private void calculate() {
        if (statusText == null) return;

        updateDynamicSections();

        boolean bankTransfer = isBankTransfer();
        boolean cashWithdrawal = isCashWithdrawal();
        boolean tracksWallet = tracksWalletBalance();
        boolean tracksBank = tracksBankBalance();
        boolean hasWallet = hasAmount(walletInput);
        boolean hasBank = hasAmount(bankInput);

        long wallet = parseAmount(walletInput);
        long bank = parseAmount(bankInput);
        long amount = parseAmount(amountInput);
        FeeBand band = findBand(amount);

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
        long tax = cashWithdrawal ? withdrawalTax(amount) : 0;
        long deductions = fee + tax;
        long totalDebit = amount + fee + tax;
        long newWallet = wallet - totalDebit;
        long newBank = bank + amount;

        feeValue.setText(money(fee));
        taxValue.setText(cashWithdrawal ? money(tax) : "UGX 0");
        deductionsValue.setText(cashWithdrawal ? money(deductions) : "UGX 0");
        totalDebitValue.setText(money(totalDebit));
        walletValue.setText(tracksWallet ? (hasWallet ? money(newWallet) : "Enter wallet before") : "Not tracked");
        bankValue.setText(tracksBank ? (hasBank ? money(newBank) : "Enter bank before") : "Not tracked");
        updateSplitSuggestion(amount, deductions);

        if (tracksWallet && hasWallet && newWallet < 0) {
            statusText.setText("Insufficient wallet balance after fee.");
            statusText.setTextColor(danger);
        } else {
            statusText.setText(balanceStatus(hasWallet, hasBank, bankTransfer, tracksWallet, tracksBank));
            statusText.setTextColor(selectedProvider().equals("MTN") ? Color.rgb(136, 102, 0) : airtelRed);
        }
    }

    private void showEmptyResult(String message) {
        feeValue.setText("UGX 0");
        taxValue.setText("UGX 0");
        deductionsValue.setText("UGX 0");
        totalDebitValue.setText("UGX 0");
        walletValue.setText(tracksWalletBalance() ? "Enter wallet before" : "Not tracked");
        bankValue.setText(tracksBankBalance() ? "Enter bank before" : "Not tracked");
        hideSplitSuggestion();
        statusText.setText(message);
        statusText.setTextColor(muted);
    }

    private void updateSplitSuggestion(long amount, long singleCost) {
        currentSplitOption = findBestSplit(amount, singleCost);
        if (currentSplitOption == null || splitSaveButton == null) {
            hideSplitSuggestion();
            return;
        }
        splitSaveButton.setText("Split to Save " + money(currentSplitOption.saving));
        splitSaveButton.setVisibility(View.VISIBLE);
    }

    private void hideSplitSuggestion() {
        currentSplitOption = null;
        if (splitSaveButton != null) {
            splitSaveButton.setVisibility(View.GONE);
        }
    }

    private SplitOption findBestSplit(long amount, long singleCost) {
        if (amount <= 0 || singleCost <= 0) return null;

        FeeBand[] bands = selectedBands();
        Set<Long> candidates = new HashSet<>();
        candidates.add(amount / 2);
        candidates.add(amount - (amount / 2));

        for (FeeBand band : bands) {
            addSplitCandidate(candidates, band.min, amount);
            addSplitCandidate(candidates, band.max, amount);
            addSplitCandidate(candidates, amount - band.min, amount);
            addSplitCandidate(candidates, amount - band.max, amount);
        }

        SplitOption best = null;
        for (Long firstPart : candidates) {
            if (firstPart == null || firstPart <= 0 || firstPart >= amount) continue;
            long secondPart = amount - firstPart;
            long firstCost = transactionCost(firstPart, bands);
            long secondCost = transactionCost(secondPart, bands);
            if (firstCost < 0 || secondCost < 0) continue;

            long splitCost = firstCost + secondCost;
            long saving = singleCost - splitCost;
            if (saving <= 0) continue;

            long displayFirst = Math.max(firstPart, secondPart);
            long displaySecond = Math.min(firstPart, secondPart);
            if (best == null || saving > best.saving || (saving == best.saving && displayFirst > best.firstPart)) {
                best = new SplitOption(displayFirst, displaySecond, singleCost, splitCost, saving);
            }
        }
        return best;
    }

    private void addSplitCandidate(Set<Long> candidates, long candidate, long amount) {
        if (candidate > 0 && candidate < amount) {
            candidates.add(candidate);
        }
    }

    private long transactionCost(long amount, FeeBand[] bands) {
        FeeBand band = findBand(amount, bands);
        if (band == null) return -1;
        long tax = isCashWithdrawal() ? withdrawalTax(amount) : 0;
        return band.fee + tax;
    }

    private void showSplitOptionDialog() {
        if (currentSplitOption == null) return;

        String message =
                "Current option\n" +
                        "Send " + money(parseAmount(amountInput)) + " once\n" +
                        "Total fee/deductions: " + money(currentSplitOption.singleCost) + "\n\n" +
                        "Better split option\n" +
                        "Send " + money(currentSplitOption.firstPart) + " + " + money(currentSplitOption.secondPart) + "\n" +
                        "Total fee/deductions: " + money(currentSplitOption.splitCost) + "\n\n" +
                        "You may save " + money(currentSplitOption.saving) + ".";

        new AlertDialog.Builder(this)
                .setTitle("Split to Save")
                .setMessage(message)
                .setPositiveButton("Got it", null)
                .show();
    }

    private String balanceStatus(boolean hasWallet, boolean hasBank, boolean bankTransfer, boolean tracksWallet, boolean tracksBank) {
        if (!tracksWallet && !tracksBank) {
            return "Fee calculated for " + selectedProviderDisplayName() + " " + selectedTransactionName() + ".";
        }
        if (tracksWallet && !hasWallet) {
            return "Fee applied. Enter wallet balance before to see the after balance.";
        }
        if (tracksBank && !hasBank) {
            return "Wallet after is ready. Enter bank balance before to see bank after.";
        }
        if (bankTransfer && tracksBank) {
            return "Wallet and bank balances updated for this Mobile to Bank transfer.";
        }
        return "Wallet balance updated for this " + selectedTransactionName() + ".";
    }

    private FeeBand findBand(long amount) {
        return findBand(amount, selectedBands());
    }

    private FeeBand findBand(long amount, FeeBand[] bands) {
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
        if (type == CASH_WITHDRAWAL) return mtn ? MTN_CASH_WITHDRAWAL : AIRTEL_CASH_WITHDRAWAL;
        if (type == BILL_PAYMENT) return mtn ? MTN_BILL_PAYMENT : AIRTEL_BILL_PAYMENT;
        if (type == PREMIUM_BILL_PAYMENT) return mtn ? MTN_PREMIUM_BILL_PAYMENT : AIRTEL_PREMIUM_BILL_PAYMENT;
        return mtn ? MTN_MOBILE_TO_MOBILE : AIRTEL_MOBILE_TO_MOBILE;
    }

    private int selectedTransactionType() {
        int position = transactionTypeSpinner == null ? 0 : transactionTypeSpinner.getSelectedItemPosition();
        if (position == 1) return MOBILE_TO_BANK;
        if (position == 2) return CASH_WITHDRAWAL;
        if (position == 3) return BILL_PAYMENT;
        if (position == 4) return PREMIUM_BILL_PAYMENT;
        return MOBILE_TO_MOBILE;
    }

    private String selectedTransactionName() {
        int type = selectedTransactionType();
        if (type == MOBILE_TO_BANK) return "Mobile to Bank";
        if (type == CASH_WITHDRAWAL) return "Cash Withdrawal";
        if (type == BILL_PAYMENT) return "Bill Payment";
        if (type == PREMIUM_BILL_PAYMENT) return "Premium Bill Payment";
        return "Mobile to Mobile";
    }

    private boolean isBankTransfer() {
        return selectedTransactionType() == MOBILE_TO_BANK;
    }

    private boolean isCashWithdrawal() {
        return selectedTransactionType() == CASH_WITHDRAWAL;
    }

    private boolean tracksWalletBalance() {
        return selectedBalanceMode() > 0;
    }

    private boolean tracksBankBalance() {
        return isBankTransfer() && selectedBalanceMode() == 2;
    }

    private int selectedBalanceMode() {
        return balanceModeSpinner == null ? 0 : balanceModeSpinner.getSelectedItemPosition();
    }

    private int indexOf(String[] values, String target) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    private String selectedProvider() {
        return providerSpinner != null && providerSpinner.getSelectedItemPosition() == 1 ? "MTN" : "Airtel";
    }

    private String selectedProviderDisplayName() {
        return providerSpinner != null && providerSpinner.getSelectedItemPosition() == 1 ? "MTN MoMo" : "Airtel Money";
    }

    private long parseAmount(EditText input) {
        if (input == null) return 0;
        return parseDigits(digitsOnly(input));
    }

    private boolean hasAmount(EditText input) {
        return input != null && !digitsOnly(input).isEmpty();
    }

    private String digitsOnly(EditText input) {
        if (input == null) return "";
        return input.getText().toString().replaceAll("[^0-9]", "");
    }

    private long parseDigits(String digits) {
        if (digits == null || digits.isEmpty()) return 0;
        try {
            return Long.parseLong(digits);
        } catch (NumberFormatException ignored) {
            return 0;
        }
    }

    private String money(long amount) {
        return "UGX " + formatPlain(amount);
    }

    private long withdrawalTax(long amount) {
        return (long) Math.ceil(amount * 0.005d);
    }

    private String formatPlain(long amount) {
        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }

    private LinearLayout card() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(18), dp(18), dp(18), dp(18));
        card.setBackground(makeRoundRect(Color.WHITE, Color.rgb(219, 234, 235), dp(8)));
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
        final long taxMin;
        final long taxMax;

        FeeBand(long min, long max, long fee) {
            this(min, max, fee, 0, 0);
        }

        FeeBand(long min, long max, long fee, long taxMin, long taxMax) {
            this.min = min;
            this.max = max;
            this.fee = fee;
            this.taxMin = taxMin;
            this.taxMax = taxMax;
        }
    }

    private static class SplitOption {
        final long firstPart;
        final long secondPart;
        final long singleCost;
        final long splitCost;
        final long saving;

        SplitOption(long firstPart, long secondPart, long singleCost, long splitCost, long saving) {
            this.firstPart = firstPart;
            this.secondPart = secondPart;
            this.singleCost = singleCost;
            this.splitCost = splitCost;
            this.saving = saving;
        }
    }
}
