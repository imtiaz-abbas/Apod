//
//  DetailPage.swift
//  iosApp
//
//  Created by able-macmini-007 on 05/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct DetailPage: View {
  var picture: AstronomyPicture?
  
  var body: some View {
    return VStack {
      Text(picture?.explanation ?? "")
    }
  }
}
