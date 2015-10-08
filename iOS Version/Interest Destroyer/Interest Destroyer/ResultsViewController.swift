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
}