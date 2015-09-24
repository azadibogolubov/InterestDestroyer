//
//  WelcomeView.swift
//  Interest Destroyer
//
//  Created by Azadi on 3/2/15.
//  Copyright (c) 2015 Tutor Azadi. All rights reserved.
//

import UIKit

class GreenGradientView: UIView {
    override func drawRect(rect: CGRect) {
        var context = UIGraphicsGetCurrentContext()
        CGContextSaveGState(context);
        var colorSpace = CGColorSpaceCreateDeviceRGB()
        var startColor = UIColor(
            red: 94.0 / 255.0,
            green: 156.0 / 255.0,
            blue: 86.0 / 255.0,
            alpha: 1.0)
        var startColorComponents = CGColorGetComponents(startColor.CGColor)
        var endColor = UIColor(
            red: 141.0 / 255.0,
            green: 240.0 / 255.0,
            blue: 130.0 / 255.0,
            alpha: 1.0)
        var endColorComponents = CGColorGetComponents(
            endColor.CGColor)
        var colorComponents = [
            startColorComponents[0],
            startColorComponents[1],
            startColorComponents[2],
            startColorComponents[3],
            endColorComponents[0],
            endColorComponents[1],
            endColorComponents[2],
            endColorComponents[3]]
        var locations:[CGFloat] = [0.0, 1.0]
        var gradient = CGGradientCreateWithColorComponents(
            colorSpace,
            &colorComponents,
            &locations,2)
        var startPoint = CGPointMake(
            0,
            self.bounds.height)
        var endPoint = CGPointMake(
            self.bounds.width,
            self.bounds.height)
        CGContextDrawLinearGradient(
            context,
            gradient,
            startPoint,
            endPoint,
            CGGradientDrawingOptions(rawValue: 0))
        CGContextRestoreGState(context);
    }
}
