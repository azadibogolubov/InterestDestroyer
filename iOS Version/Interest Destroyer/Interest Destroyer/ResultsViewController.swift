//
//  ResultsViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 2/26/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class ResultsViewController: UIViewController {

    @IBOutlet weak var resultLabel: UILabel!
    var segueString: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        resultLabel.text = segueString
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
