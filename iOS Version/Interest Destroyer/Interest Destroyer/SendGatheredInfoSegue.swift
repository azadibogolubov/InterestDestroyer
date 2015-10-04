//
//  SendGatheredInfoSegue.swift
//  Interest Destroyer
//
//  Created by Azadi on 10/3/15.
//  Copyright © 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class SendGatheredInfoSegue: UIStoryboardSegue {
    override func perform() {
        super.perform()
        _ = self.sourceViewController.view as UIView!
        _ = self.destinationViewController.view as UIView!
    }
}
