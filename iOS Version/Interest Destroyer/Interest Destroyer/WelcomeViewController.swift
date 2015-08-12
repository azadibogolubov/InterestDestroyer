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
    
    // Doubles
    var rate: Double = 0.0
    var principal: Double = 0.0
    var principal_original: Double = 0.0
    var time: Double = 0.0
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

