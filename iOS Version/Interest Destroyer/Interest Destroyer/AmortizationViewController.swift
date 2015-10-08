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
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return min_principal_paid.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var tableCell: AmortizationTableViewCell = self.tableView.dequeueReusableCellWithIdentifier("customCell") as! AmortizationTableViewCell
        var monthNum = indexPath.row + 1
        var currRow = indexPath.row
        tableCell.monthLbl.text = "Month #: \(monthNum)"
        //println("mpp count: \(min_principal_paid.count)")
        //println("epp count: \(extra_principal_paid.count)")
        let formattedMinPrincipal = String(format:"%.2f", min_principal_paid[currRow])
        let formattedExtraPrincipal = String(format:"%.2f", extra_principal_paid[currRow])
       if (currRow <= min_principal_paid.count) {
        tableCell.minimumPaymentLbl.text = "Minimum payment: $\(formattedMinPrincipal)"
        }
        if (currRow < extra_principal_paid.count) {
            tableCell.extraPaymentLbl.text = "Extra payment: $\(formattedExtraPrincipal)"
        }
        else
        {
            tableCell.extraPaymentLbl.text = "Extra payment: $0.00"
        }
        return tableCell
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        tableView.deselectRowAtIndexPath(indexPath, animated: true)
        
        let row = indexPath.row
        print(min_principal_paid[row])
    }
}