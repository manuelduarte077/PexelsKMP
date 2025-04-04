//
//  LicenseCategory.swift
//  Conducir
//
//  Created by Manuel Duarte on 4/4/25.
//

import Foundation

struct LicenseCategory: Identifiable {
    let id = UUID()
    let name: String
    let description: String
    let imageName: String
}

