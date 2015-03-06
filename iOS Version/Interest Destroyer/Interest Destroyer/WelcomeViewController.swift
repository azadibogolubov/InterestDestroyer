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
    
    func amortize(principal: Float, rate: Float, time: Float)
    {
        let reducedRate = rate / 1200
        var result = Double(principal) * Double(reducedRate)
        result = result * (pow(Double((1.0+reducedRate)), Double(time)))
        result = Double(result / Double(pow(Double(1.0+reducedRate),Double(time))-1))
        result = result / Double(1.0)
        println("Result is \(result)")
    }
    
    let a: Double = 100000.0
    let b: Double = 5.25
    let c: Double = 360
}
