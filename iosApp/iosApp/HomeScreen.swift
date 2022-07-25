import SwiftUI
import shared

class ApodViewModel: ObservableObject {
  @Published var pictures: [AstronomyPicture] = []
  
  func getAPOD() {
    self.pictures = AstronomyPictureDao().listPictures()
  }
}

struct HomeScreen: View {
  @ObservedObject var viewModel = ApodViewModel()
  @State var selectedPicture: AstronomyPicture?
  @State var showingDetailScreen = false

  private func fetchPicturesFromDb() {
    DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
      viewModel.getAPOD()
    }
  }

	var body: some View {
    NavigationView {
      VStack {
        ScrollView(.vertical, showsIndicators: false) {
          GridRowSection(pictures: viewModel.pictures) { picture in
            self.selectedPicture = picture
            self.showingDetailScreen = true
          }
        }
        NavigationLink(isActive: $showingDetailScreen) {
          DetailPage(picture: selectedPicture)
            .navigationTitle(selectedPicture?.title ?? "")
        } label: {
          EmptyView()
        }
      }
    }
    .onAppear {
      self.fetchPicturesFromDb()
    }
	}
}

struct HomeScreen_Previews: PreviewProvider {
	static var previews: some View {
    HomeScreen()
	}
}
