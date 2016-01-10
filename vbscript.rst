========
VBScript
========
以下 VBScript 用來把 ``src.pptx`` 的每一頁投影片都輸出到 ``exported-pictures/`` 裡面

::

  pwd = CreateObject("Scripting.FileSystemObject").GetParentFolderName(WScript.ScriptFullName)
  src_filename = "src.pptx"
  target_folder = "exported-pictures/"

  Set PowerPointApp = CreateObject("PowerPoint.Application")
  Set PresentationObj = PowerPointApp.Presentations.Open(pwd & "\" & src_filename, False, False, False)
  For Each slide In PresentationObj.Slides
      ' MsgBox slide.SlideIndex
      slide.Export pwd & "\" & target_folder & slide.SlideIndex & ".png", "PNG"
  Next
  MsgBox "Done, total " & PresentationObj.Slides.Count & " pages"
  PowerPointApp.Quit
