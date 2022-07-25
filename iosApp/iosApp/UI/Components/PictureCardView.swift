//
//  PictureCardView.swift
//  iosApp
//
//  Created by able-macmini-007 on 05/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct TwoColumnRow: View {
  var chunk: [AstronomyPicture]
  var onItemClick: (AstronomyPicture) -> Void
  var body: some View {
    return HStack(spacing: 20) {
      PictureCardView(picture: chunk.first!).onTapGesture {
        onItemClick(chunk.first!)
      }
      if let picture = chunk.count > 1 ? chunk.last! : nil {
        PictureCardView(picture: picture)
          .onTapGesture {
            onItemClick(picture)
          }
      } else {
        Spacer()
      }
    }
  }
}

struct GridRowSection: View {
  var pictures: [AstronomyPicture]
  var onItemClick: (AstronomyPicture) -> Void

  var body: some View {
    let chunkedPictures = pictures.chunked(into: 2)
    return VStack(spacing: 20) {
      ForEach(chunkedPictures, id: \.first!.date) { chunk in
        TwoColumnRow(chunk: chunk, onItemClick: onItemClick)
      }
    }
    .padding(.horizontal, 20)
  }
}

struct PictureCardView: View {
  var picture: AstronomyPicture

  var body: some View {
    ZStack(alignment: .bottomLeading) {
      AsyncImage(url: URL(string: picture.url)) { phase in
          switch phase {
          case .empty:
              ProgressView()
          case .success(let image):
              image.resizable()
                   .aspectRatio(contentMode: .fill)
                   .frame(maxWidth: ((UIScreen.main.bounds.width - 40) / 2) - 10, maxHeight: 200)
          case .failure:
              Image(systemName: "photo")
          @unknown default:
              EmptyView()
          }
      }
      VStack {
        Spacer()
        HStack {
          VStack(alignment: .leading, spacing: 10) {
            Text(picture.date.getReadableTime())
              .foregroundColor(.white)
              .font(.init(.caption))
              .fontWeight(.bold)
            Text(picture.title)
              .foregroundColor(.white)
              .font(.init(.footnote))
          }
          Spacer()
        }
        .padding(10)
        .background(Color.black.opacity(0.5))
      }
    }
    .frame(width: ((UIScreen.main.bounds.width - 40) / 2) - 10, height: 200, alignment: .center)
    .cornerRadius(10)
    .padding(.bottom, 10)
    .shadow(
      color: Color(hex: "#52000000"),
      radius: 12,
      x: 0,
      y: 8
    )
  }
}
