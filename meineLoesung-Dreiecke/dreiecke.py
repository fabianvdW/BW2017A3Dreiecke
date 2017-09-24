import itertools
import sys


def get_diff(a, b):
    if a - b > 0:
        return a - b
    else:
        return b - a


def read_input(input_file):
    output = []
    with open(input_file, 'rb') as f:
        for line in f.readlines():
            line.rstrip('\n').rstrip('\r')
            values = line.split(' ')
            if len(values) == 4:
                output += [[[float(values[0]), float(values[1])], [float(values[2]), float(values[3])]]]
    return output


def calculate_functions(points):
    output = []
    for point_pair in points:
        x1 = point_pair[0][0]
        y1 = point_pair[0][1]
        x2 = point_pair[1][0]
        y2 = point_pair[1][1]
        # Ueberpruefen, ob die Steigung berechnet werden kann (geht nicht, wenn die beiden Punkte den selben x-Wert haben)
        if x2 != x1:
            a = (y2 - y1) / (x2 - x1)
            b = y1 - a * x1
            if x1 < x2:
                output += [[a, b, [x1, x2]]]
            else:
                output += [[a, b, [x2, x1]]]
        else:
            # a ist der x-Wert der beiden Punkte
            a = x1
            b = 'vertical'
            if y1 < y2:
                output += [[a, b, [y1, y2]]]
            else:
                output += [[a, b, [y2, y1]]]
    return output


def already_in_output(output, point_list):
    for list in itertools.permutations(point_list, 3):
        list = make_to_list(list)
        if list in output:
            return True
    return False


def round_all(a, b, c, d, e, f):
    return round(a, 2), round(b, 2), round(c, 2), round(d, 2), round(e, 2), round(f, 2)


def make_to_list(list):
    output = []
    for item in list:
        output += [item]
    return output


def calculate_vertical_intersection(func1, func2, func3):
    if func2[0] != func3[0]:
        d1 = [func1[2][0], func1[2][1]]  # Definitionsmenge von func1
        d2 = [func2[2][0], func2[2][1]]  # Definitionsmenge von func2
        d3 = [func3[2][0], func3[2][1]]  # Definitionsmenge von func3
        s1 = func2[0] * func1[0] + func2[1]  # Schnittpunkt von func1 und func2 (y)
        s2 = func3[0] * func1[0] + func3[1]  # Schnittpunkt von func1 und func3 (y)
        s3 = (func2[1] - func3[1]) / (func3[0] - func2[0])  # Schnittpunkt von func2 und func3 (x)
        if ((d1[0] <= s1 <= d1[1]) and (d2[0] <= func1[0] <= d2[1])) and (
                    (d1[0] <= s2 <= d1[1]) and (d3[0] <= func1[0] <= d3[1])) and (
                    (d2[0] <= s3 <= d2[1]) and (d3[0] <= s3 <= d3[1])):
            x1 = func1[0]
            x2 = func1[0]
            y3 = func2[0] * s3 + func2[1]
            s1, s2, s3, y1, y2, y3 = round_all(x1, x2, s3, s1, s2, y3)
            return [[s1, y1], [s2, y2], [s3, y3]]


def remove_paranthes(list):
    output = ''
    for point in list:
        for item in point:
            output += str(item) + ' '
    return output[:-1]


def calculate_intersections(functions):
    output = []
    for func1 in functions:
        for func2 in functions:
            for func3 in functions:
                # Festlegen der Definitionsmengen
                d1 = [func1[2][0], func1[2][1]]  # Definitionsmenge von func1
                d2 = [func2[2][0], func2[2][1]]  # Definitionsmenge von func2
                d3 = [func3[2][0], func3[2][1]]  # Definitionsmenge von func3
                # One line is vertical
                if func1[1] == 'vertical' and func2[1] != 'vertical' and func3[1] != 'vertical':
                    result = calculate_vertical_intersection(func1, func2, func3)
                    if result is not None and not already_in_output(output, result):
                        output += [result]
                elif func2[1] == 'vertical' and func1[1] != 'vertical' and func3[1] != 'vertical':
                    result = calculate_vertical_intersection(func2, func1, func3)
                    if result is not None and not already_in_output(output, result):
                        output += [result]
                elif func3[1] == 'vertical' and func2[1] != 'vertical' and func1[1] != 'vertical':
                    result = calculate_vertical_intersection(func3, func2, func1)
                    if result is not None and not already_in_output(output, result):
                        output += [result]
                # no line is vertical (i.e. they're all functions)
                elif func1[1] != 'vertical' and func2[1] != 'vertical' and func3[1] != 'vertical':
                    # Pruefen, ob 2 Funktionen parallel zueinander sind (falls ja, gibt es kein Dreieck)
                    if (func1[0] != func2[0]) and (func1[0] != func3[0]) and (func2[0] != func3[0]):
                        # Schnittpunkte berechnen
                        s1 = (func2[1] - func1[1]) / (func1[0] - func2[0])  # Schnittpunkt von func1 und func2
                        s2 = (func3[1] - func1[1]) / (func1[0] - func3[0])  # Schnittpunkt von func1 und func3
                        s3 = (func2[1] - func3[1]) / (func3[0] - func2[0])  # Schnittpunkt von func2 und func3
                        # Pruefen, ob die Schnittpunkte in der Definitionsmenge liegen
                        if ((d1[0] <= s1 <= d1[1]) and (d2[0] <= s1 <= d2[1])) and (
                                    (d1[0] <= s2 <= d1[1]) and (d3[0] <= s2 <= d3[1])) and (
                                    (d2[0] <= s3 <= d2[1]) and (d3[0] <= s3 <= d3[1])):
                            y1 = func1[0] * s1 + func1[1]
                            y2 = func1[0] * s2 + func1[1]
                            y3 = func2[0] * s3 + func2[1]
                            s1, s2, s3, y1, y2, y3 = round_all(s1, s2, s3, y1, y2, y3)
                            list = [[s1, y1], [s2, y2], [s3, y3]]
                            if not already_in_output(output, list):
                                output += [list]
    return output


if len(sys.argv) < 3:
    print "Syntax: dreiecke.py <eingabe_datei> <ausgabe_datei>"
    sys.exit(0)

points = read_input(sys.argv[1])
functions = calculate_functions(points)
intersections = calculate_intersections(functions)
with open(sys.argv[2], 'wb') as f:
    f.write(str(len(intersections)))
    for triangle in intersections:
        f.write('\n' + remove_paranthes(triangle))
