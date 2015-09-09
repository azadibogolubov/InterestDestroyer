//
//  MainViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 8/30/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class MainViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // Variables
    // Ints
    var payoff_years: Int = 0
    var payoff_months: Int = 0
    var time: Int = 0
    
    // Doubles
    var rate: Double = 0.0
    var principal: Double = 0.0
    var principal_original: Double = 0.0
    var monthly_payment: Double = 0.0
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
    
/*    override func shouldPerformSegueWithIdentifier(identifier: String!, sender: AnyObject!) -> Bool {
        if identifier == "getGraphicalResultsSegue" {
        }
        return true
    }*/
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if (segue.identifier == "getGraphicalResultsSegue")
        {
            performCalculations()
            println("Extra payments count: \(extra_payments.count)")
            println("Min payments count: \(minimum_payments.count)")
            println("Min principal remaining count: \(min_principal_remaining.count)") // GOOD
            println("Extra principal remaining count: \(extra_principal_remaining.count)")
            println("min interest paid count: \(min_interest_paid.count)") // GOOD
            println("extra interest paid count: \(extra_interest_paid.count)")
            println("Min principal paid count: \(min_principal_paid.count)") // GOOD
            println("Extra principal paid count: \(extra_principal_paid.count)")

            let vc = segue.destinationViewController as! GraphicalResultsViewController
            vc.timeSaved = timeSaved
            vc.interestSaved = interestSaved
            vc.time = time
            vc.extra_payment = extra_payment
            vc.payment_amount = monthly_payment
            vc.principal = principal
            vc.rate = rate
            vc.min_principal_paid = min_principal_paid
            vc.extra_principal_paid = extra_principal_paid
            vc.min_interest_paid = min_interest_paid
            vc.extra_interest_paid = extra_interest_paid
            vc.min_principal_remaining = min_principal_remaining
            vc.extra_principal_remaining = extra_principal_remaining
        }
    }
    
    func performCalculations()
    {
        simple_interest = principal * (rate / 100) * (Double(time) / 12)
        interest_paid = 0.00
        net_interest = 0.00
        principal_paid = 0.00
        monthly_payment = amortize(principal, rate: rate, time: time)
        
        principal_original = principal
        
        for var i = 0; i < time; i++ {
            compound_interest = principal * (1.0 + (rate / 1200)) - principal
            if compound_interest <= 0 {
                break
            }
            interest_paid += compound_interest;
            min_interest_paid.append(compound_interest)
            principal_paid = monthly_payment - compound_interest
            min_principal_paid.append(principal_paid)
            min_principal_remaining.append(principal)
            principal -= principal_paid
        }
        
        original_interest = interest_paid
            
        interest_paid = 0
        compound_interest = 0
        principal_paid = 0
        simple_interest = 0
        
        println("Monthly payment amount: \(monthly_payment)")
        principal = principal_original
        for var i = 0; i < time; i++ {
            compound_interest = principal * (1 + (rate / 1200)) - principal
            if (compound_interest <= 0) {
                payoff_months = i
                break;
            }
            interest_paid += compound_interest
            extra_interest_paid.append(interest_paid - extra_payment)
            principal_paid = (monthly_payment + extra_payment) - compound_interest
            extra_principal_paid.append(principal_paid)
            extra_principal_remaining.append(principal)
            principal -= principal_paid
        }
        timeSaved = Double((time - payoff_months) / 12);
        interestSaved = original_interest - interest_paid;
    }
    
    func stringToDouble(#stringToConvert: UITextField!) -> Double
    {
        return Double(stringToConvert.text.toInt()!)
    }
    
    func stringToInt(#stringToConvert: UITextField!) -> Int
    {
        return stringToConvert.text.toInt()!
    }
    
    func amortize(principal: Double, var rate: Double, time: Int) -> Double {
        rate = Double(rate / 1200.0)
        monthly_payment = (principal * rate * pow((1.0 + rate), Double(time))) / (pow((1.0 + rate), Double(time)) - 1.0)
        return monthly_payment
    }
}
