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
    

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        var resultsViewController: ResultsViewController = segue.destinationViewController as ResultsViewController
        //var principal = principalTxt.text.toInt()
        //var interest = (interestTxt.text as NSString).floatValue
        var numMonths = numMonthsTxt.text.toInt()
        var extraPayment = extraPaymentTxt.text.toInt()

        //var principalString = "Principal: $\(principal!)"
        //var interestString = "Interest: \(interest)%"
        var numMonthsString = "Number of Months: \(numMonths!)"
        var extraPaymentString = "Extra Payment: $\(extraPayment!)"
        
        let principal: Float = 100000
        let rate: Float = 5.25
        let time: Int = 360
        
        var extra_payment_amount = 100.0;
        var total_interest = 0.0
        var compound_interest: Float = 0.0
        var principal_paid = 0.0
        var simple_interest = 0.0
        var remainingPrincipal = principal
        
        //resultsViewController.principalString = principalString
        //resultsViewController.interestString = interestString
        resultsViewController.numMonthsString = numMonthsString
        resultsViewController.extraPaymentString = extraPaymentString
        
        println(String(format: "Monthly payment amount: $%.2f", amortize(principal,rate,time)))
        // Make a for loop to get principal payments.
        for var i = 0; i < time; i++
        {
            compound_interest = (remainingPrincipal * (1 + (rate / 1200)) - remainingPrincipal)
            if compound_interest <= 0 {
                break;
            }
            
            total_interest += Double(compound_interest);
            principal_paid = (amortize(principal,rate,time) + extra_payment_amount) - Double(compound_interest)
            remainingPrincipal -= Float(principal_paid)
        }
        println(String(format: "Total interest paid: $%.2f", total_interest))}
    }
    
    
    func amortize(principal: Float, rate: Float, time: Int) -> Double
    {
        let reducedRate = rate / 1200
        var result = Double(principal) * Double(reducedRate)
        result = result * (pow(Double((1.0+reducedRate)), Double(time)))
        result = Double(result / Double(pow(Double(1.0+reducedRate),Double(time))-1))
        result = result / Double(1.0)
        return result
    }
    
    
