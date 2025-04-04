//
//  Feature.swift
//  Conducir
//
//  Created by Manuel Duarte on 4/4/25.
//

import Foundation

struct Feature: Identifiable {
    let id = UUID()
    let title: String
    let description: String
    let systemName: String
}
