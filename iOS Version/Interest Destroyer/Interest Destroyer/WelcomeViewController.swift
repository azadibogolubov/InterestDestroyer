//
//  ViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 2/24/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

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
        var principal = principalTxt.text.toInt()
        var interest = (interestTxt.text as NSString).floatValue
        var numMonths = numMonthsTxt.text.toInt()
        var extraPayment = extraPaymentTxt.text.toInt()

        var principalString = "Principal: $\(principal!)"
        var interestString = "Interest: \(interest)%"
        var numMonthsString = "Number of Months: \(numMonths!)"
        var extraPaymentString = "Extra Payment: $\(extraPayment!)"
        
        resultsViewController.principalString = principalString
        resultsViewController.interestString = interestString
        resultsViewController.numMonthsString = numMonthsString
        resultsViewController.extraPaymentString = extraPaymentString
        
    }
    
    func amortize(principal: NSDecimalNumber, rate: NSDecimalNumber, time: NSDecimalNumber)  {
        var result: Double = Double(rate) / Double(1200.00)
        // principal * rate * Math.pow((1 + rate), time))/(Math.pow((1+rate),time)-1
    }
}
