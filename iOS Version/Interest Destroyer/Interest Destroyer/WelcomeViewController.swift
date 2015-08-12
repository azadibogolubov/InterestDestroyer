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
    var rate: Double
    var principal: Double
    var principal_original: Double
    var time: Double
    var payment_amount: Double
    var extra_payment: Double
    var simple_interest: Double
    var compound_interest: Double
    var original_interest: Double
    var net_interest: Double
    var interestSaved: Double
    var principal_paid: Double
    var payoff_years: Int
    var payoff_months: Int
    
    Double[] extra_payments, minimum_payments, min_principal_remaining, extra_principal_remaining;
    Double interest_paid = original_interest = 0.00f;
    Double timeSaved;
    Double[] min_interest_paid, extra_interest_paid, min_principal_paid, extra_principal_paid;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func shouldPerformSegueWithIdentifier(identifier: String!, sender: AnyObject!) -> Bool {
        if identifier == "getResults" {
        }
        return true
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if (segue.identifier == "getResults")
        {
        var resultsViewController: ResultsViewController = segue.destinationViewController as! ResultsViewController
        }
    }
}

