//
//  ViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 2/24/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

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
        resultsViewController.segueString = "Successful data sent to results."
    }


}

