# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.14

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Users\Jon Olav\AppData\Local\JetBrains\Toolbox\apps\CLion\ch-0\192.5728.100\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Users\Jon Olav\AppData\Local\JetBrains\Toolbox\apps\CLion\ch-0\192.5728.100\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "C:\Users\Jon Olav\Documents\dat233\dailyneighbours"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles/dailyneighbours.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/dailyneighbours.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/dailyneighbours.dir/flags.make

CMakeFiles/dailyneighbours.dir/main.cpp.obj: CMakeFiles/dailyneighbours.dir/flags.make
CMakeFiles/dailyneighbours.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="C:\Users\Jon Olav\Documents\dat233\dailyneighbours\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/dailyneighbours.dir/main.cpp.obj"
	C:\mingw64\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\dailyneighbours.dir\main.cpp.obj -c "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\main.cpp"

CMakeFiles/dailyneighbours.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/dailyneighbours.dir/main.cpp.i"
	C:\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\main.cpp" > CMakeFiles\dailyneighbours.dir\main.cpp.i

CMakeFiles/dailyneighbours.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/dailyneighbours.dir/main.cpp.s"
	C:\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\main.cpp" -o CMakeFiles\dailyneighbours.dir\main.cpp.s

CMakeFiles/dailyneighbours.dir/solver.cpp.obj: CMakeFiles/dailyneighbours.dir/flags.make
CMakeFiles/dailyneighbours.dir/solver.cpp.obj: ../solver.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="C:\Users\Jon Olav\Documents\dat233\dailyneighbours\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/dailyneighbours.dir/solver.cpp.obj"
	C:\mingw64\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\dailyneighbours.dir\solver.cpp.obj -c "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\solver.cpp"

CMakeFiles/dailyneighbours.dir/solver.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/dailyneighbours.dir/solver.cpp.i"
	C:\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\solver.cpp" > CMakeFiles\dailyneighbours.dir\solver.cpp.i

CMakeFiles/dailyneighbours.dir/solver.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/dailyneighbours.dir/solver.cpp.s"
	C:\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\solver.cpp" -o CMakeFiles\dailyneighbours.dir\solver.cpp.s

CMakeFiles/dailyneighbours.dir/solver_helpers.cpp.obj: CMakeFiles/dailyneighbours.dir/flags.make
CMakeFiles/dailyneighbours.dir/solver_helpers.cpp.obj: ../solver_helpers.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="C:\Users\Jon Olav\Documents\dat233\dailyneighbours\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/dailyneighbours.dir/solver_helpers.cpp.obj"
	C:\mingw64\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\dailyneighbours.dir\solver_helpers.cpp.obj -c "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\solver_helpers.cpp"

CMakeFiles/dailyneighbours.dir/solver_helpers.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/dailyneighbours.dir/solver_helpers.cpp.i"
	C:\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\solver_helpers.cpp" > CMakeFiles\dailyneighbours.dir\solver_helpers.cpp.i

CMakeFiles/dailyneighbours.dir/solver_helpers.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/dailyneighbours.dir/solver_helpers.cpp.s"
	C:\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\solver_helpers.cpp" -o CMakeFiles\dailyneighbours.dir\solver_helpers.cpp.s

# Object files for target dailyneighbours
dailyneighbours_OBJECTS = \
"CMakeFiles/dailyneighbours.dir/main.cpp.obj" \
"CMakeFiles/dailyneighbours.dir/solver.cpp.obj" \
"CMakeFiles/dailyneighbours.dir/solver_helpers.cpp.obj"

# External object files for target dailyneighbours
dailyneighbours_EXTERNAL_OBJECTS =

dailyneighbours.exe: CMakeFiles/dailyneighbours.dir/main.cpp.obj
dailyneighbours.exe: CMakeFiles/dailyneighbours.dir/solver.cpp.obj
dailyneighbours.exe: CMakeFiles/dailyneighbours.dir/solver_helpers.cpp.obj
dailyneighbours.exe: CMakeFiles/dailyneighbours.dir/build.make
dailyneighbours.exe: CMakeFiles/dailyneighbours.dir/linklibs.rsp
dailyneighbours.exe: CMakeFiles/dailyneighbours.dir/objects1.rsp
dailyneighbours.exe: CMakeFiles/dailyneighbours.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="C:\Users\Jon Olav\Documents\dat233\dailyneighbours\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_4) "Linking CXX executable dailyneighbours.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\dailyneighbours.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/dailyneighbours.dir/build: dailyneighbours.exe

.PHONY : CMakeFiles/dailyneighbours.dir/build

CMakeFiles/dailyneighbours.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\dailyneighbours.dir\cmake_clean.cmake
.PHONY : CMakeFiles/dailyneighbours.dir/clean

CMakeFiles/dailyneighbours.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" "C:\Users\Jon Olav\Documents\dat233\dailyneighbours" "C:\Users\Jon Olav\Documents\dat233\dailyneighbours" "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\cmake-build-debug" "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\cmake-build-debug" "C:\Users\Jon Olav\Documents\dat233\dailyneighbours\cmake-build-debug\CMakeFiles\dailyneighbours.dir\DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/dailyneighbours.dir/depend

