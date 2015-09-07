//
//  MainViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 8/30/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class MainViewController: UIViewController {

    var principal: Int? = 0
    var interest: Float? = 0.0
    var monthsRemaining: Int? = 0
    var extraPayment: Int? = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Begin tests
        assert(principal != 0, "Principal failed")
        assert(interest != 0, "Interest failed")
        assert(monthsRemaining != 0, "Months remaining failed")
        assert(extraPayment != 0, "Extra payment failed")
        // End tests
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
