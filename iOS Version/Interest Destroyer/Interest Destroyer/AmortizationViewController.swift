//
//  AmortizationViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 9/8/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class AmortizationViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var tableView: UITableView!
    
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
    let textCellIdentifier = "cell"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.delegate = self
        tableView.dataSource = self
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

    
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return min_principal_paid.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier(textCellIdentifier, forIndexPath: indexPath) as! UITableViewCell
        
        let row = indexPath.row
        var currentValue = String("Minimum payment: $\(min_principal_paid[row])")
        cell.textLabel?.text = currentValue
        
        return cell
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        tableView.deselectRowAtIndexPath(indexPath, animated: true)
        
        let row = indexPath.row
        println(min_principal_paid[row])
    }
}