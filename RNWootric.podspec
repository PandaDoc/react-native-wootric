
Pod::Spec.new do |s|
  s.name         = "RNWootric"
  s.version      = "1.0.0"
  s.summary      = "RNWootric"
  s.description  = <<-DESC
                  RNWootric
                   DESC
  s.homepage     = "https://github.com/Wootric/react-native-wootric"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "8.0"
  s.source       = { :git => "https://github.com/author/RNWootric.git", :tag => "master" }
  s.source_files  = "ios/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency "WootricSDK", "~> 0.12.0"

end

  
