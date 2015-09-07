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

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let vc = segue.destinationViewController as? MainViewController
        if (segue.identifier == "sendGatheredInfoSegue")
        {
            vc!.principal = principalTxt.text.toInt()
            vc!.interest = (interestTxt.text as NSString).floatValue
            vc!.monthsRemaining = monthsRemainingTxt.text.toInt()
            vc!.extraPayment = extraPaymentTxt.text.toInt()
        }
    }
}
