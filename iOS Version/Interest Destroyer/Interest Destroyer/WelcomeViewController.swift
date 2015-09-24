//
//  ViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 2/24/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit
import Foundation

class WelcomeViewController: UIViewController {
    @IBOutlet weak var principalTxt: UITextField!
    @IBOutlet weak var interestTxt: UITextField!
    @IBOutlet weak var numMonthsTxt: UITextField!
    @IBOutlet weak var extraPaymentTxt: UITextField!
    @IBOutlet weak var getMyResultsBtn: UIButton!
    
    // Variables
    // Ints
    var payoff_years: Int = 0
    var payoff_months: Int = 0
    var time: Int = 0
    
    // Doubles
    var rate: Double = 0.0
    var principal: Double = 0.0
    var principal_original: Double = 0.0
    var payment_amount: Double = 0.0
    var extra_payment: Double = 0.0
    var simple_interest: Double = 0.0
    var compound_interest: Double = 0.0
    var original_interest: Double = 0.0
    var net_interest: Double = 0.0
    var interestSaved: Double = 0.0
    var principal_paid: Double = 0.0
    var interest_paid: Double = 0.0
    var timeSaved: Double = 0.0
    
    // Double arrays
    var extra_payments: [Double] = []
    var minimum_payments: [Double] = []
    var min_principal_remaining: [Double] = []
    var extra_principal_remaining: [Double] = []
    var min_interest_paid: [Double] = []
    var extra_interest_paid: [Double] = []
    var min_principal_paid: [Double] = []
    var extra_principal_paid: [Double] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func shouldPerformSegueWithIdentifier(identifier: String, sender: AnyObject!) -> Bool {
        if identifier == "getResults" {
            performCalculations()
        }
        return true
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if (segue.identifier == "getResults")
        {
            //var resultsViewController: ResultsViewController = segue.destinationViewController as! ResultsViewController
            /*
            TODO: Convert this intent logic from Java to Swift for segue.
            extra.putDouble("TIME_SAVED", timeSaved);
            extra.putDouble("INTEREST_SAVED", interestSaved);
            extra.putDouble("TOTAL_MONTHS", time);
            extra.putDouble("EXTRA_PAYMENTS", extra_payment);
            extra.putDouble("MINIMUM_PAYMENTS", payment_amount);
            extra.putString("PRINCIPAL", principalTxt.getText().toString());
            extra.putString("INTEREST_RATE", interestTxt.getText().toString());
            extra.putString("EXTRA_PAYMENT_AMOUNT", extraPaymentTxt.getText().toString());
            extra.putDoubleArray("MIN_PRINCIPAL_PAID", min_principal_paid);
            extra.putDoubleArray("EXTRA_PRINCIPAL_PAID", extra_principal_paid);
            extra.putDoubleArray("MIN_INTEREST_PAID", min_interest_paid);
            extra.putDoubleArray("EXTRA_INTEREST_PAID", extra_interest_paid);
            extra.putDoubleArray("MIN_PRINCIPAL_REMAINING", min_principal_remaining);
            extra.putDoubleArray("EXTRA_PRINCIPAL_REMAINING", extra_principal_remaining);*/
        }
    }
    
    func performCalculations()
    {
        principal = principal_original;
        simple_interest = principal * (rate / 100) * (Double(time) / 12);
        interest_paid = 0.00;
        net_interest = 0.00;
        principal_paid = 0.00;
        payment_amount = amortize(principal, rate: rate, time: time);
        
        if principalTxt.text?.characters.count < 4 {
            print("Minimum amount for principal must be greater than $1000.")
        }
        else if numMonthsTxt.text?.characters.count < 1 {
            print("Minimum number of months must be greater than 0.")
        }
        else if interestTxt.text?.characters.count < 1 {
            print("Minimum interest rate must be greater than 0%.")
            return;
        }
        
        principal_original = stringToDouble(stringToConvert: principalTxt)
        principal = stringToDouble(stringToConvert: principalTxt)
        time = stringToInt(stringToConvert: numMonthsTxt)
        rate = stringToDouble(stringToConvert: interestTxt)
        extra_payment = stringToDouble(stringToConvert: extraPaymentTxt)
        
        for var i = 0; i < time; i++ {
            minimum_payments[i] = 0.00;
            extra_payments[i] = 0.00;
        }
        
        payment_amount = amortize(principal, rate: rate, time: time);
        for var i = 0; i < time; i++ {
            compound_interest = principal * (1.0 + (rate / 1200)) - principal
            if compound_interest <= 0 {
                break
            }
            interest_paid += compound_interest;
            min_interest_paid[i] = compound_interest;
            principal_paid = payment_amount - compound_interest;
            min_principal_paid[i] = principal_paid;
            min_principal_remaining[i] = principal;
            principal -= principal_paid;
        }
        
        original_interest = interest_paid;
        
        principal_original = stringToDouble(stringToConvert: principalTxt!)
        principal = stringToDouble(stringToConvert: principalTxt!)
        time = stringToInt(stringToConvert: numMonthsTxt!)
        rate = stringToDouble(stringToConvert: interestTxt!)
        extra_payment = stringToDouble(stringToConvert: extraPaymentTxt!)
        
        interest_paid = 0
        compound_interest = 0
        principal_paid = 0
        simple_interest = 0
        
        payment_amount = amortize(principal, rate: rate, time: time)
        print("Monthly payment amount: \(payment_amount)")
        for var i = 0; i < time; i++ {
            compound_interest = principal * (1 + (rate / 1200)) - principal
            if (compound_interest <= 0) {
                payoff_months = i;
                break;
            }
            interest_paid += compound_interest;
            extra_interest_paid[i] = interest_paid - extra_payment;
            principal_paid = (payment_amount + extra_payment) - compound_interest;
            extra_principal_paid[i] = principal_paid;
            extra_principal_remaining[i] = principal;
            principal -= principal_paid;
        }
        timeSaved = Double((time - payoff_months) / 12);
        interestSaved = original_interest - interest_paid;
    }
    
    func stringToDouble(stringToConvert stringToConvert: UITextField!) -> Double
    {
        return Double(stringToConvert.text!)!
    }
    
    func stringToInt(stringToConvert stringToConvert: UITextField!) -> Int
    {
        return Int(stringToConvert.text!)!
    }
    
    func amortize(principal: Double, var rate: Double, time: Int) -> Double {
        rate = Double(rate / 1200.0)
        return (principal * rate * pow((1.0 + rate), Double(time))) / (pow((1.0 + rate), Double(time)) - 1.0);
    }
}

