//
//  ResultsViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 2/26/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class ResultsViewController: UIViewController {

    @IBOutlet weak var principalLabel: UILabel!
    @IBOutlet weak var interestLabel: UILabel!
    @IBOutlet weak var numMonthsLabel: UILabel!
    @IBOutlet weak var extraPaymentsLabel: UILabel!
    
    var principalString: String = ""
    var interestString: String = ""
    var numMonthsString: String = ""
    var extraPaymentString: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        principalLabel.text = principalString
        interestLabel.text = interestString
        numMonthsLabel.text = numMonthsString
        extraPaymentsLabel.text = extraPaymentString
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
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
