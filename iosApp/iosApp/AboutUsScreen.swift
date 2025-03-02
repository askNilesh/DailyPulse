//
//  AboutUsScreen.swift
//  iosApp
//
//  Created by Nilesh Rathod on 30/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct AboutListView : View {
    
    private struct RowItem :  Hashable{
        let title: String
        let body : String
    }
    
    
    private let items : [RowItem] = {
        let platForm = PlatForm()
        var result : [RowItem] = [
            .init(title: "Operating System", body: "\(platForm.osName) \(platForm.osVersion)"),
            .init(title: "Device", body: "\(platForm.deviceModel)"),
            .init(title: "Density", body: "@\(platForm.density)x"),
        ]
        return result
    }()
    var body: some View {
        List {
            ForEach(items,id: \.self ){ item in
                VStack(alignment: .leading){
                    Text(item.title)
                        .font(.footnote)
                    
                    Text(item.body)
                        .font(.body)
                }
            }
        }
    }
}
