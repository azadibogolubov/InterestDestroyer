//
//  GraphicalResultsViewController.swift
//  Interest Destroyer
//
//  Created by Azadi on 9/6/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit
import Charts

class GraphicalResultsViewController: UIViewController {
    
    @IBOutlet weak var barChartView: BarChartView!
    @IBOutlet weak var yearsSavedLbl: UILabel!
    @IBOutlet weak var interestSavedLbl: UILabel!
    
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
        for var i = 0; i < time; i++ {
            columns.append("")
        }
        let values = min_principal_remaining
        let eValues = extra_principal_remaining
        yearsSavedLbl.text = "Years saved: \(timeSaved)"
        interestSavedLbl.text = "Interest Saved: $\(Int(interestSaved))"
        setChart(columns, values: values, eValues: eValues)
    }
    
    func setChart(dataPoints: [String], values: [Double], eValues: [Double]) {
        var entries: [BarChartDataEntry] = []
        var counter = 0
        for i in 0..<values.count {
            let entry = BarChartDataEntry(value: values[i], xIndex: i)
            entries.append(entry)
        }
        
        var entries2: [BarChartDataEntry] = []
        counter = 0
        for i in 0..<eValues.count {
            let entry = BarChartDataEntry(value: eValues[i], xIndex: i)
            entries2.append(entry)
        }
        
        let chartDataSet = BarChartDataSet(yVals: entries, label: "Minimum Principal Remaining (in thousands)")
        let chartDataSet2 = BarChartDataSet(yVals: entries2, label: "Extra Principal Remaining (in thousands)")
        
        let colors: [UIColor] = [
            UIColor(red: 0.0/255.0, green: 0.0/255.0, blue: 255.0/255.0, alpha: 1.0) ]
        let colors2: [UIColor] = [
            UIColor(red: 0.0/255.0, green: 255/255.0, blue: 0.0/255.0, alpha: 1.0) ]
        
        chartDataSet.colors = colors
        chartDataSet2.colors = colors2
        
        let chartData = BarChartData(xVals: columns, dataSet: chartDataSet)
        chartData.addDataSet(chartDataSet2)
        
        barChartView.data = chartData
    }
}

