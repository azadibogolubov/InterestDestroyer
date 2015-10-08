//
//  AmortizationTableViewCell.swift
//  Interest Destroyer
//
//  Created by Azadi on 9/10/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class AmortizationTableViewCell: UITableViewCell {

    @IBOutlet weak var monthLbl: UILabel!
    @IBOutlet weak var minimumPaymentLbl: UILabel!
    @IBOutlet weak var extraPaymentLbl: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

}
