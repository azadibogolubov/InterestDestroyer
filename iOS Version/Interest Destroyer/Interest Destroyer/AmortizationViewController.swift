//
//  AmortizationViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 9/8/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class AmortizationViewController: UIViewController {

    var columns: [String] = []
    var timeSaved: Double = 0.0
    var interestSaved: Double = 0.0
    var time: Int = 0
    var extra_payment: Double = 0.0
    var payment_amount: Double = 0.0
    var principal: Double = 0.0
    var rate: Double = 0.0
    var min_principal_paid: [Double] = []
    var extra_principal_paid: [Double] = []
    var min_interest_paid: [Double] = []
    var extra_interest_paid: [Double] = []
    var min_principal_remaining: [Double] = []
    var extra_principal_remaining: [Double] = []

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
