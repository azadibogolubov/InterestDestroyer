//
//  InformationViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 8/31/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class InformationViewController: UIViewController {

    @IBOutlet weak var principalTxt: UITextField!
    @IBOutlet weak var interestTxt: UITextField!
    @IBOutlet weak var monthsRemainingTxt: UITextField!
    @IBOutlet weak var extraPaymentTxt: UITextField!

    @IBOutlet weak var emptyLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func resultsBtnClick(sender: AnyObject) {

        if principalTxt.text == "" || interestTxt.text == "" || monthsRemainingTxt == "" || extraPaymentTxt == "" {
            emptyLabel.hidden = false
        }
        else {
            self.performSegueWithIdentifier("sendGatheredInfoSegue", sender: nil)
        }
    }

    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let vc = segue.destinationViewController as? MainViewController
            vc!.principal = NSString(string: principalTxt.text!).doubleValue
            vc!.rate = NSString(string: interestTxt.text!).doubleValue
            vc!.time = Int(monthsRemainingTxt.text!)!
            vc!.extra_payment = NSString(string: extraPaymentTxt.text!).doubleValue
    }
}
